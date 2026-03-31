package com.rays.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a standardized API response object used across all ORS controllers.
 * <p>
 * Encapsulates a success flag and a result map containing response data,
 * messages, and input validation errors. All controller endpoints return
 * an instance of this class to provide a consistent response structure
 * to the client.
 * </p>
 *
 * @author Krati Trivedi
 */
public class ORSResponse {

    /**
     * Key used to store input validation errors in the result map.
     */
    public static final String INPUT_ERROR = "inputerror";

    /**
     * Key used to store response messages in the result map.
     */
    public static final String MESSAGE = "message";

    /**
     * Key used to store response data (DTOs or lists) in the result map.
     */
    public static final String DATA = "data";

    /**
     * The result map holding all response entries such as data, messages,
     * and input errors, keyed by their respective constant identifiers.
     */
    private Map<String, Object> result = new HashMap<String, Object>();

    /**
     * Indicates whether the operation was successful.
     * Defaults to {@code false}.
     */
    public boolean success = false;

    /**
     * Default constructor that creates an {@code ORSResponse} with
     * {@code success} set to {@code false}.
     */
    public ORSResponse() {
    }

    /**
     * Constructor that creates an {@code ORSResponse} with the specified success flag.
     *
     * @param success {@code true} if the operation was successful, {@code false} otherwise
     */
    public ORSResponse(boolean success) {
        this.success = success;
    }

    /**
     * Returns whether the operation was successful.
     *
     * @return {@code true} if successful, {@code false} otherwise
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the success flag for this response.
     *
     * @param success {@code true} if the operation was successful, {@code false} otherwise
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Returns the full result map containing all response entries.
     *
     * @return a {@link Map} of result key-value pairs
     */
    public Map<String, Object> getResult() {
        return result;
    }

    /**
     * Replaces the entire result map with the given map.
     *
     * @param result the {@link Map} to set as the result
     */
    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    /**
     * Adds input validation errors to the result map under the key {@link #INPUT_ERROR}.
     *
     * @param value the validation error object, typically a {@link Map} of field-error pairs
     */
    public void addInputError(Object value) {
        result.put(INPUT_ERROR, value);
    }

    /**
     * Adds a response message to the result map under the key {@link #MESSAGE}.
     *
     * @param value the message to include in the response
     */
    public void addMessage(Object value) {
        result.put(MESSAGE, value);
    }

    /**
     * Adds response data (such as a DTO or list of DTOs) to the result map
     * under the key {@link #DATA}.
     *
     * @param value the data object to include in the response
     */
    public void addData(Object value) {
        result.put(DATA, value);
    }

    /**
     * Adds a custom key-value entry to the result map.
     * <p>
     * Use this method to include additional response attributes beyond
     * the standard data, message, and input error entries.
     * </p>
     *
     * @param key   the key under which the value will be stored
     * @param value the value to associate with the given key
     */
    public void addResult(String key, Object value) {
        result.put(key, value);
    }
}