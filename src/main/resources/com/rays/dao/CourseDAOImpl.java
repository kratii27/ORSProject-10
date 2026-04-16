package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.CourseDTO;

/**
 * Data Access Object (DAO) implementation for {@link CourseDTO} in the ORS application.
 * <p>
 * Extends {@link BaseDAOImpl} to inherit common JPA-based CRUD operations and
 * implements {@link CourseDAOInt}. Provides entity-specific implementations for
 * retrieving the DTO class type and building dynamic search filter predicates
 * based on course attributes.
 * </p>
 *
 * @author Krati Trivedi
 */
@Repository
public class CourseDAOImpl extends BaseDAOImpl<CourseDTO> implements CourseDAOInt {

    /**
     * Returns the {@link Class} type of {@link CourseDTO} managed by this DAO.
     *
     * @return {@code CourseDTO.class}
     */
    @Override
    public Class<CourseDTO> getDTOClass() {
        // TODO Auto-generated method stub
        return CourseDTO.class;
    }

    /**
     * Builds and returns a list of JPA {@link Predicate} conditions for filtering
     * course records based on the non-empty fields of the given {@link CourseDTO}.
     * <p>
     * The following fields are used for filtering when non-empty, each using a
     * prefix-based LIKE condition:
     * <ul>
     *   <li>{@code name} — filters courses whose name starts with the given value</li>
     *   <li>{@code description} — filters courses whose description starts with the given value</li>
     *   <li>{@code duration} — filters courses whose duration starts with the given value</li>
     * </ul>
     * </p>
     *
     * @param dto             the {@link CourseDTO} containing filter values
     * @param criteriaBuilder the {@link CriteriaBuilder} used to construct predicates
     * @param qRoot           the {@link Root} of the criteria query for {@link CourseDTO}
     * @return a list of {@link Predicate} conditions for the WHERE clause
     */
    @Override
    public List<Predicate> getWhereClause(CourseDTO dto, CriteriaBuilder criteriaBuilder, Root<CourseDTO> qRoot) {
        List<Predicate> conditions = new ArrayList<Predicate>();

        if (!isEmptyString(dto.getName())) {
            conditions.add(criteriaBuilder.like(qRoot.get("name"), dto.getName() + "%"));
        }
        if (!isEmptyString(dto.getDescription())) {
            conditions.add(criteriaBuilder.like(qRoot.get("description"), dto.getDescription() + "%"));
        }
        if (!isEmptyString(dto.getDuration())) {
            conditions.add(criteriaBuilder.like(qRoot.get("duration"), dto.getDuration() + "%"));
        }

        return conditions;
    }
}