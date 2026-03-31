package com.rays.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rays.exception.DuplicateRecordException;

/**
 * Generic base service interface defining standard business logic operations
 * for all service classes in the ORS application.
 * <p>
 * All service interfaces should extend this interface, parameterized with
 * the appropriate DTO type, to inherit common CRUD and search method contracts.
 * </p>
 *
 * @param <T> the DTO type extending {@link BaseDTO}
 *
 * @author Krati Trivedi
 */
public interface BaseServiceInt<T extends BaseDTO> {

    /**
     * Updates an existing DTO record in the database.
     *
     * @param dto         the DTO with updated values
     * @param userContext the current user's context used for audit fields
     */
    public void update(T dto, UserContext userContext);

    /**
     * Saves a DTO record by either adding a new record or updating an existing one.
     * <p>
     * If the DTO has a valid ID, an update is performed; otherwise a new record is added.
     * </p>
     *
     * @param dto         the DTO to be saved or updated
     * @param userContext the current user's context
     * @return the primary key ID of the saved or updated record
     */
    public long save(T dto, UserContext userContext);

    /**
     * Deletes a record by its primary key.
     *
     * @param id          the primary key of the record to delete
     * @param userContext the current user's context
     */
    public void delete(long id, UserContext userContext);

    /**
     * Retrieves a single DTO record by its primary key.
     *
     * @param pk          the primary key of the record to find
     * @param userContext the current user's context
     * @return the matching DTO, or {@code null} if no record is found
     */
    public T findByPk(long pk, UserContext userContext);

    /**
     * Retrieves a single DTO record matching a specific attribute-value pair.
     *
     * @param attribute   the entity attribute name to filter by
     * @param value       the value to match against the given attribute
     * @param userContext the current user's context
     * @return the matching DTO if found, otherwise {@code null}
     */
    public T findByUniqueKey(String attribute, Object value, UserContext userContext);

    /**
     * Searches for DTO records matching the given criteria with pagination support.
     *
     * @param dto         the DTO containing search/filter criteria
     * @param pageNo      the zero-based page number to retrieve
     * @param pageSize    the number of records per page
     * @param userContext the current user's context
     * @return a list of matching DTO records for the specified page
     */
    public List<T> search(T dto, int pageNo, int pageSize, UserContext userContext);

    /**
     * Searches for all DTO records matching the given criteria without pagination.
     *
     * @param dto         the DTO containing search/filter criteria
     * @param userContext the current user's context
     * @return a list of all matching DTO records
     */
    public List search(T dto, UserContext userContext);
}