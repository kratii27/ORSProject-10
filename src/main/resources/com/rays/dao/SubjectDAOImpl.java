package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.common.UserContext;
import com.rays.dto.CourseDTO;
import com.rays.dto.SubjectDTO;

/**
 * Data Access Object (DAO) implementation for {@link SubjectDTO} in the ORS application.
 * <p>
 * Extends {@link BaseDAOImpl} to inherit common JPA-based CRUD operations and
 * implements {@link SubjectDAOInt}. Provides entity-specific implementations for
 * retrieving the DTO class type, building dynamic search filter predicates based on
 * subject attributes, and populating derived fields before persistence.
 * </p>
 *
 * @author Krati Trivedi
 */
@Repository
public class SubjectDAOImpl extends BaseDAOImpl<SubjectDTO> implements SubjectDAOInt {

    /**
     * DAO for retrieving {@link CourseDTO} data used to populate
     * the course name on the subject record before persistence.
     */
    @Autowired
    CourseDAOInt courseDao;

    /**
     * Returns the {@link Class} type of {@link SubjectDTO} managed by this DAO.
     *
     * @return {@code SubjectDTO.class}
     */
    @Override
    public Class<SubjectDTO> getDTOClass() {
        // TODO Auto-generated method stub
        return SubjectDTO.class;
    }

    /**
     * Builds and returns a list of JPA {@link Predicate} conditions for filtering
     * subject records based on the non-empty fields of the given {@link SubjectDTO}.
     * <p>
     * The following fields are used for filtering when non-empty, each using a
     * prefix-based LIKE condition:
     * <ul>
     *   <li>{@code name} — filters subjects whose name starts with the given value</li>
     *   <li>{@code courseName} — filters subjects whose associated course name starts with the given value</li>
     * </ul>
     * </p>
     *
     * @param dto             the {@link SubjectDTO} containing filter values
     * @param criteriaBuilder the {@link CriteriaBuilder} used to construct predicates
     * @param qRoot           the {@link Root} of the criteria query for {@link SubjectDTO}
     * @return a list of {@link Predicate} conditions for the WHERE clause
     */
    @Override
    public List<Predicate> getWhereClause(SubjectDTO dto, CriteriaBuilder criteriaBuilder, Root<SubjectDTO> qRoot) {

        List<Predicate> conditions = new ArrayList<Predicate>();

        if (!isEmptyString(dto.getName())) {
            conditions.add(criteriaBuilder.like(qRoot.get("name"), dto.getName() + "%"));
        }
        if (!isEmptyString(dto.getCourseName())) {
            conditions.add(criteriaBuilder.like(qRoot.get("courseName"), dto.getCourseName() + "%"));
        }
        return conditions;
    }

    /**
     * Populates derived fields on the {@link SubjectDTO} before it is persisted.
     * <p>
     * If a valid {@code courseId} is present on the DTO, the corresponding
     * {@link CourseDTO} is retrieved and the course name is set on the subject record.
     * </p>
     *
     * @param dto         the {@link SubjectDTO} to be populated
     * @param userContext the current user's context
     */
    @Override
    protected void populate(SubjectDTO dto, UserContext userContext) {
        if (dto.getCourseId() != null && dto.getCourseId() > 0) {
            CourseDTO courseDTO = courseDao.findByPk(dto.getCourseId(), userContext);
            dto.setCourseName(courseDTO.getName());
        }
    }
}