package com.rays.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.exception.DatabaseException;

/**
 * Generic base service implementation providing transactional business logic
 * for common CRUD operations in the ORS application.
 * <p>
 * All service implementation classes should extend this class, parameterized
 * with the appropriate DTO and DAO types, to inherit standard save, update,
 * delete, and search functionality.
 * </p>
 *
 * @param <T> the DTO type extending {@link BaseDTO}
 * @param <D> the DAO type extending {@link BaseDAOInt}
 *
 * @author Krati Trivedi
 */
@Transactional
public class BaseServiceImpl<T extends BaseDTO, D extends BaseDAOInt<T>> implements BaseServiceInt<T> {

    /**
     * The DAO instance auto-injected for performing data access operations.
     */
    @Autowired
    protected D dao;

    /**
     * Updates an existing DTO record in the database within a required transaction.
     * <p>
     * Before updating, retrieves the existing record to preserve the original
     * {@code createdBy} and {@code createdDateTime} audit fields.
     * </p>
     *
     * @param dto         the DTO with updated values
     * @param userContext the current user's context used for audit fields
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(T dto, UserContext userContext) {
        T existDto = findByPk(dto.getId(), userContext);
        System.out.println("before find by pk" + existDto.toString());
        if (existDto != null) {
            dto.setCreatedBy(existDto.getCreatedBy());
            dto.setCreatedDateTime(existDto.getCreatedDateTime());
        }
        dao.update(dto, userContext);
    }

    /**
     * Saves a DTO record by either adding a new record or updating an existing one,
     * within a required transaction.
     * <p>
     * If the DTO's ID is non-null and greater than zero, an update is performed.
     * Otherwise, a new record is added via the DAO.
     * </p>
     *
     * @param dto         the DTO to be saved or updated
     * @param userContext the current user's context
     * @return the primary key ID of the saved or updated record
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public long save(T dto, UserContext userContext) {
        Long id = dto.getId();
        if (id != null && id > 0) {
            update(dto, userContext);
        } else {
            id = dao.add(dto, userContext);
        }
        return id;
    }

    /**
     * Deletes a record by its primary key within a required transaction.
     * <p>
     * First retrieves the record by its ID; if found, proceeds with deletion.
     * </p>
     *
     * @param id          the primary key of the record to delete
     * @param userContext the current user's context
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(long id, UserContext userContext) {
        T dto = dao.findByPk(id, userContext);
        if (dto != null) {
            dao.delete(dto, userContext);
        }
    }

    /**
     * Retrieves a single DTO record by its primary key in a read-only transaction.
     *
     * @param pk          the primary key of the record to find
     * @param userContext the current user's context
     * @return the matching DTO, or {@code null} if no record is found
     */
    @Transactional(readOnly = true)
    public T findByPk(long pk, UserContext userContext) {
        T dto = dao.findByPk(pk, userContext);
        return dto;
    }

    /**
     * Retrieves a single DTO record matching a specific attribute-value pair
     * in a read-only transaction.
     *
     * @param attribute   the entity attribute name to filter by
     * @param value       the value to match against the given attribute
     * @param userContext the current user's context
     * @return the matching DTO if found, otherwise {@code null}
     */
    @Transactional(readOnly = true)
    public T findByUniqueKey(String attribute, Object value, UserContext userContext) {
        return dao.findByUniqueKey(attribute, value, userContext);
    }

    /**
     * Searches for DTO records matching the given criteria with pagination support,
     * in a read-only transaction.
     *
     * @param dto         the DTO containing search/filter criteria
     * @param pageNo      the zero-based page number to retrieve
     * @param pageSize    the number of records per page
     * @param userContext the current user's context
     * @return a list of matching DTO records for the specified page
     */
    @Transactional(readOnly = true)
    public List<T> search(T dto, int pageNo, int pageSize, UserContext userContext) {
        return dao.search(dto, pageNo, pageSize, userContext);
    }

    /**
     * Searches for all DTO records matching the given criteria without pagination,
     * in a read-only transaction.
     *
     * @param dto         the DTO containing search/filter criteria
     * @param userContext the current user's context
     * @return a list of all matching DTO records
     */
    @Transactional(readOnly = true)
    public List<T> search(T dto, UserContext userContext) {
        return dao.search(dto, userContext);
    }
}