package com.rays.common;

/**
 * Generic base form class used to carry input data from the client
 * to the controller layer in the ORS application.
 * <p>
 * Subclasses should extend this class with entity-specific fields
 * and override {@link #getDto()} to return a populated DTO instance.
 * </p>
 *
 * @param <T> the DTO type extending {@link BaseDTO}
 *
 * @author Krati Trivedi
 */
public class BaseForm<T extends BaseDTO> {

    /**
     * The primary key ID of the entity being submitted via this form.
     * A {@code null} or zero value indicates a new record (add operation),
     * while a positive value indicates an existing record (update operation).
     */
    protected Long id;

    /**
     * Returns the primary key ID held by this form.
     *
     * @return the {@link Long} ID, or {@code null} if not set
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the primary key ID on this form.
     *
     * @param id the {@link Long} ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the DTO populated from this form's fields.
     * <p>
     * Subclasses should override this method to construct and return
     * an entity-specific DTO instance with all relevant fields mapped.
     * </p>
     *
     * @return the populated DTO of type {@code T}, or {@code null} in the base implementation
     */
    public T getDto() {
        return null;
    }

    /**
     * Initializes the given DTO with the ID from this form.
     * <p>
     * If the form's ID is non-null and greater than zero, it is set on the DTO
     * to indicate an update operation. Otherwise, the DTO's ID is set to
     * {@code null} to indicate a new record addition.
     * </p>
     *
     * @param dto the DTO instance to initialize
     * @return the initialized DTO with its ID set appropriately
     */
    public T initDTO(T dto) {
        if (id != null && id > 0) {
            dto.setId(id);
        } else {
            dto.setId(null);
        }
        return dto;
    }
}