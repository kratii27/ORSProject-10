package com.rays.config;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class for generating, validating, and extracting claims from
 * JSON Web Tokens (JWT) in the ORS application.
 * <p>
 * Uses HMAC-SHA256 ({@code HS256}) for signing tokens and Base64 URL encoding
 * for header and payload serialization. Token secret and expiration duration
 * are injected from application properties.
 * </p>
 *
 * @author Krati Trivedi
 */
@Component
public class JWTUtil {

    /**
     * The secret key used for signing and validating JWT tokens.
     * Injected from the {@code jwt.secret} application property.
     */
    @Value("${jwt.secret}")
    private String jwtSecret;

    /**
     * The token expiration duration in milliseconds.
     * Injected from the {@code jwt.expiration} application property.
     */
    @Value("${jwt.expiration}")
    private long jwtExpiration;

    /**
     * Shared {@link ObjectMapper} instance used for JSON serialization
     * and deserialization of JWT header and payload.
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Generates a signed JWT token for the given user details.
     * <p>
     * The token contains the following claims in the payload:
     * subject (loginId), userId, role, issued-at timestamp, and expiration timestamp.
     * The token is signed using HMAC-SHA256 and returned as a Base64 URL-encoded string.
     * </p>
     *
     * @param userId  the unique identifier of the user
     * @param loginId the login ID (typically email) of the user, used as the JWT subject
     * @param role    the role assigned to the user
     * @return a signed JWT token string in the format {@code header.payload.signature}
     * @throws Exception if an error occurs during JSON serialization or HMAC signing
     */
    public String generateToken(Long userId, String loginId, String role) throws Exception {
        long nowMillis = System.currentTimeMillis() / 1000;
        long expMillis = nowMillis + (jwtExpiration / 1000);
        System.out.println("token expiration time: " + jwtExpiration);

        // JWT Header
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        // JWT Payload (claims)
        Map<String, Object> payload = new HashMap<>();
        payload.put("sub", loginId);   // loginId as subject
        payload.put("userId", userId); // numeric user id
        payload.put("role", role);     // user role
        payload.put("iat", nowMillis); // issued at
        payload.put("exp", expMillis); // expiration

        String headerBase64 = encodeUrl(objectMapper.writeValueAsString(header));
        String payloadBase64 = encodeUrl(objectMapper.writeValueAsString(payload));
        String signatureBase64 = sign(headerBase64 + "." + payloadBase64, jwtSecret);

        return String.join(".", headerBase64, payloadBase64, signatureBase64);
    }

    /**
     * Validates the given JWT token against the expected login ID.
     * <p>
     * Performs the following checks:
     * <ul>
     *   <li>Token structure must contain exactly three parts</li>
     *   <li>Signature must match the recomputed HMAC-SHA256 signature</li>
     *   <li>Subject (loginId) must match the provided {@code expectedLoginId}</li>
     *   <li>Token must not be expired</li>
     * </ul>
     * </p>
     *
     * @param token           the JWT token string to validate
     * @param expectedLoginId the expected login ID to match against the token subject
     * @return {@code true} if the token is valid
     * @throws Exception if the token is invalid, tampered with, or expired
     */
    public boolean validateToken(String token, String expectedLoginId) throws Exception {
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            throw new Exception("Invalid JWT token");
        }

        String payloadJson = decode(parts[1]);
        String tokenLoginId = extractField(payloadJson, "sub");
        String expectedSignature = sign(parts[0] + "." + parts[1], jwtSecret);

        if (!expectedSignature.equals(parts[2])) {
            throw new Exception("JWT signature does not match");
        }

        if (!expectedLoginId.equals(tokenLoginId)) {
            throw new Exception("JWT subject (loginId) does not match");
        }

        if (isTokenExpired(payloadJson)) {
            throw new Exception("JWT token has expired");
        }

        return true;
    }

    /**
     * Extracts the login ID (subject claim) from the given JWT token.
     *
     * @param token the JWT token string
     * @return the login ID stored in the {@code sub} claim
     */
    public String extractLoginId(String token) {
        return extractField(decode(token.split("\\.")[1]), "sub");
    }

    /**
     * Extracts the user ID from the given JWT token.
     *
     * @param token the JWT token string
     * @return the user ID stored in the {@code userId} claim as a {@link Long}
     */
    public Long extractUserId(String token) {
        return Long.parseLong(extractField(decode(token.split("\\.")[1]), "userId"));
    }

    /**
     * Extracts the role from the given JWT token.
     *
     * @param token the JWT token string
     * @return the role stored in the {@code role} claim as a {@link String}
     */
    public String extractRole(String token) {
        return extractField(decode(token.split("\\.")[1]), "role");
    }

    /**
     * Checks whether the given JWT payload indicates an expired token.
     * <p>
     * Compares the {@code exp} claim value against the current system time in seconds.
     * </p>
     *
     * @param payloadJson the decoded JSON string of the JWT payload
     * @return {@code true} if the token has expired, {@code false} otherwise
     */
    private boolean isTokenExpired(String payloadJson) {
        long exp = Long.parseLong(extractField(payloadJson, "exp"));
        return exp < (System.currentTimeMillis() / 1000);
    }

    /**
     * Extracts the value of a specific field from a JSON string.
     *
     * @param json  the JSON string to parse
     * @param field the field name whose value is to be extracted
     * @return the field value as a {@link String}, or {@code null} if parsing fails
     */
    private String extractField(String json, String field) {
        try {
            Map<String, Object> map = objectMapper.readValue(json, Map.class);
            return String.valueOf(map.get(field));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Computes an HMAC-SHA256 signature for the given data using the provided key.
     *
     * @param data the input string to sign
     * @param key  the secret key used for signing
     * @return the Base64 URL-encoded signature string
     * @throws Exception if the HMAC-SHA256 algorithm is unavailable or the key is invalid
     */
    private String sign(String data, String key) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        return encodeUrl(mac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * Encodes the given string to a Base64 URL-encoded string without padding.
     *
     * @param data the string to encode
     * @return the Base64 URL-encoded representation of the input string
     */
    private String encodeUrl(String data) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Encodes the given byte array to a Base64 URL-encoded string without padding.
     *
     * @param data the byte array to encode
     * @return the Base64 URL-encoded representation of the input byte array
     */
    private String encodeUrl(byte[] data) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(data);
    }

    /**
     * Decodes a Base64 URL-encoded string back to its original UTF-8 string representation.
     *
     * @param data the Base64 URL-encoded string to decode
     * @return the decoded UTF-8 string
     */
    private String decode(String data) {
        return new String(Base64.getUrlDecoder().decode(data), StandardCharsets.UTF_8);
    }
}