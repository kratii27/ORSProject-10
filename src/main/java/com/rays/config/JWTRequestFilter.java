package com.rays.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rays.common.UserContext;
import com.rays.common.UserContextHolder;
import com.rays.dto.UserDTO;
import com.rays.service.JWTUserDetailsService;

/**
 * JWT request filter that intercepts every incoming HTTP request to validate
 * the JWT token and establish the security context for the ORS application.
 * <p>
 * Extends {@link OncePerRequestFilter} to ensure the filter logic is executed
 * exactly once per request. If a valid Bearer token is present in the
 * {@code Authorization} header, the filter authenticates the user and stores
 * the {@link UserContext} in the {@link UserContextHolder} for use downstream.
 * If the token is invalid or expired, a {@code 401 Unauthorized} response is returned.
 * </p>
 *
 * @author Krati Trivedi
 */
@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    /**
     * Utility class for JWT operations such as token validation and claim extraction.
     */
    @Autowired
    private JWTUtil jwtUtil;

    /**
     * Service for loading user details by login ID during JWT authentication.
     */
    @Autowired
    private JWTUserDetailsService jwtUserDetailsService;

    /**
     * Processes each HTTP request to validate the JWT token from the
     * {@code Authorization} header and set up the Spring Security context.
     * <p>
     * If the header contains a valid Bearer token, the method:
     * <ul>
     *   <li>Extracts and validates the login ID from the token</li>
     *   <li>Loads user details and sets the authentication in {@link SecurityContextHolder}</li>
     *   <li>Creates a {@link UserContext} and stores it in {@link UserContextHolder}</li>
     * </ul>
     * If the token is invalid, a {@code 401 Unauthorized} response is written
     * and the filter chain is halted.
     * </p>
     *
     * @param request     the incoming {@link HttpServletRequest}
     * @param response    the outgoing {@link HttpServletResponse}
     * @param filterChain the {@link FilterChain} to pass the request along if valid
     * @throws ServletException if a servlet error occurs during filtering
     * @throws IOException      if an I/O error occurs during filtering
     */
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        System.out.println("JWT Token ======>>>>> " + authorizationHeader);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            System.out.println("JWT Token ======>>>>> iiiiinnnnnn");
            String jwtToken = authorizationHeader.substring(7);
            try {
                String loginId = jwtUtil.extractLoginId(jwtToken);
                if (!jwtUtil.validateToken(jwtToken, loginId)) {
                    throw new Exception("Invalid JWT token");
                }

                if (loginId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    String role = jwtUtil.extractRole(jwtToken);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            loginId, null,
                            List.of(new SimpleGrantedAuthority("ROLE_" + role))
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

                UserDTO dto = new UserDTO();
                dto.setLogin(loginId);
                dto.setId(jwtUtil.extractUserId(jwtToken)); 
                System.out.println("request filter: " + dto.getLogin());
                UserContext context = new UserContext(dto);
                UserContextHolder.setContext(context);
            } catch (Exception e) {
                // Token is invalid or expired
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write(e.getMessage());
                return;
            }
        } 
        filterChain.doFilter(request, response);
    }
}