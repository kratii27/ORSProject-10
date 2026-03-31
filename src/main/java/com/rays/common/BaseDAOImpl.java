package com.rays.common;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Abstract base implementation of {@link BaseDAOInt} providing common JPA-based
 * data access operations for all DAO classes in the ORS application.
 * <p>
 * Subclasses must implement {@link #getDTOClass()} and {@link #getWhereClause}
 * to provide entity-specific type information and filtering logic.
 * </p>
 *
 * @param <T> the DTO type extending {@link BaseDTO}
 *
 * @author Krati Trivedi
 */
public abstract class BaseDAOImpl<T extends BaseDTO> implements BaseDAOInt<T> {

    /**
     * The JPA {@link EntityManager} used for all database interactions.
     * Injected by the persistence context.
     */
    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * Returns the {@link Class} type of the DTO managed by this DAO.
     * <p>
     * Must be implemented by each subclass to enable generic JPA operations.
     * </p>
     *
     * @return the {@link Class} of the DTO type {@code T}
     */
    public abstract Class<T> getDTOClass();

    /**
     * Builds and returns a list of JPA {@link Predicate} conditions for filtering
     * search results based on the given DTO's field values.
     * <p>
     * Must be implemented by each subclass to define entity-specific search criteria.
     * </p>
     *
     * @param dto             the DTO containing filter values
     * @param criteriaBuilder the {@link CriteriaBuilder} used to construct predicates
     * @param qRoot           the {@link Root} of the criteria query
     * @return a list of {@link Predicate} conditions for the WHERE clause
     */
    public abstract List<Predicate> getWhereClause(T dto, CriteriaBuilder criteriaBuilder, Root<T> qRoot);

    /**
     * Hook method for populating additional fields on the DTO before persistence.
     * <p>
     * Subclasses may override this method to set entity-specific fields
     * based on the current {@link UserContext}.
     * </p>
     *
     * @param dto         the DTO to be populated
     * @param userContext the current user's context
     */
    protected void populate(T dto, UserContext userContext) {
    }

    /**
     * Persists a new DTO record to the database.
     * <p>
     * Sets audit fields (createdBy, modifiedBy, createdDateTime, modifiedDateTime)
     * before persisting the entity.
     * </p>
     *
     * @param dto         the DTO to be added
     * @param userContext the current user's context used for audit fields
     * @return the generated primary key ID of the newly persisted record
     */
    @Override
    public long add(T dto, UserContext userContext) {
        dto.setCreatedBy(userContext.getLoginId());
        dto.setModifiedBy(userContext.getLoginId());
        dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
        dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
        populate(dto, userContext);
        entityManager.persist(dto);
        return dto.getId();
    }

    /**
     * Updates an existing DTO record in the database.
     * <p>
     * Updates the modifiedBy and modifiedDateTime audit fields before merging.
     * </p>
     *
     * @param dto         the DTO with updated values
     * @param userContext the current user's context used for audit fields
     */
    @Override
    public void update(T dto, UserContext userContext) {
        dto.setModifiedBy(userContext.getLoginId());
        dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
        populate(dto, userContext);
        entityManager.merge(dto);
    }

    /**
     * Removes an existing DTO record from the database.
     *
     * @param dto         the DTO to be deleted
     * @param userContext the current user's context
     */
    @Override
    public void delete(T dto, UserContext userContext) {
        entityManager.remove(dto);
    }

    /**
     * Retrieves a single DTO record by its primary key.
     *
     * @param pk          the primary key of the record to find
     * @param userContext the current user's context
     * @return the found DTO, or {@code null} if no record exists with the given key
     */
    @Override
    public T findByPk(long pk, UserContext userContext) {
        T dto = entityManager.find(getDTOClass(), pk);
        return dto;
    }

    /**
     * Retrieves a single DTO record matching a specific attribute-value pair.
     * <p>
     * Returns the matched record only when exactly one result is found.
     * </p>
     *
     * @param attribute   the entity attribute name to filter by
     * @param value       the value to match against the given attribute
     * @param userContext the current user's context
     * @return the matching DTO if exactly one result is found, otherwise {@code null}
     */
    @Override
    public T findByUniqueKey(String attribute, Object value, UserContext userContext) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(getDTOClass());
        Root<T> qRoot = criteriaQuery.from(getDTOClass());
        Predicate condition = builder.equal(qRoot.get(attribute), value);
        criteriaQuery.where(condition);
        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
        List<T> list = typedQuery.getResultList();
        T dto = null;
        if (list.size() == 1) {
            dto = list.get(0);
        }
        return dto;
    }

    /**
     * Searches for DTO records matching the given criteria with pagination support.
     * <p>
     * Uses {@link #getWhereClause} to build entity-specific filter predicates.
     * Pagination is applied only when {@code pageSize} is greater than zero.
     * </p>
     *
     * @param dto         the DTO containing search/filter criteria
     * @param pageNo      the zero-based page number to retrieve
     * @param pageSize    the number of records per page; set to 0 to retrieve all
     * @param userContext the current user's context
     * @return a list of matching DTO records for the specified page
     */
    @Override
    public List<T> search(T dto, int pageNo, int pageSize, UserContext userContext) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getDTOClass());
        Root<T> qRoot = criteriaQuery.from(getDTOClass());
        List<Predicate> conditions = getWhereClause(dto, criteriaBuilder, qRoot);
        for (Predicate condition : conditions) {
            criteriaQuery.where(condition);
        }
        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);

        if (pageSize > 0) {
            typedQuery.setFirstResult(pageNo * pageSize);
            typedQuery.setMaxResults(pageSize);
        }
        return typedQuery.getResultList();
    }

    /**
     * Searches for all DTO records matching the given criteria without pagination.
     * <p>
     * Delegates to {@link #search(BaseDTO, int, int, UserContext)} with page values of 0.
     * </p>
     *
     * @param dto         the DTO containing search/filter criteria
     * @param userContext the current user's context
     * @return a list of all matching DTO records
     */
    @Override
    public List<T> search(T dto, UserContext userContext) {
        return search(dto, 0, 0, userContext);
    }
    
    public List marksheetMeritList(String hql, UserContext userContext) {
		Query q = entityManager.createQuery(hql);
		q.setFirstResult(0);
		q.setMaxResults(10);
		List l = q.getResultList();
		return l;
	}

    /**
     * Checks whether the given string value is null or blank.
     *
     * @param val the string to check
     * @return {@code true} if the string is null or contains only whitespace, otherwise {@code false}
     */
    protected boolean isEmptyString(String val) {
        return val == null || val.trim().length() == 0;
    }

    /**
     * Checks whether the given {@link Double} value is null or zero.
     *
     * @param val the Double value to check
     * @return {@code true} if the value is null or zero, otherwise {@code false}
     */
    protected boolean isZeroNumber(Double val) {
        return val == null || val == 0;
    }

    /**
     * Checks whether the given {@link Long} value is null or zero.
     *
     * @param val the Long value to check
     * @return {@code true} if the value is null or zero, otherwise {@code false}
     */
    protected boolean isZeroNumber(Long val) {
        return val == null || val == 0;
    }

    /**
     * Checks whether the given {@link Integer} value is null or zero.
     *
     * @param val the Integer value to check
     * @return {@code true} if the value is null or zero, otherwise {@code false}
     */
    protected boolean isZeroNumber(Integer val) {
        return val == null || val == 0;
    }

    /**
     * Checks whether the given object is not null.
     *
     * @param val the object to check
     * @return {@code true} if the object is not null, otherwise {@code false}
     */
    protected boolean isNotNull(Object val) {
        return val != null;
    }
}