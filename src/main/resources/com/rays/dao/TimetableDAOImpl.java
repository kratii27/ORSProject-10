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
import com.rays.dto.TimetableDTO;

/**
 * Data Access Object (DAO) implementation for {@link TimetableDTO} in the ORS application.
 * <p>
 * Extends {@link BaseDAOImpl} to inherit common JPA-based CRUD operations and
 * implements {@link TimetableDAOInt}. Provides entity-specific implementations for
 * retrieving the DTO class type, building dynamic search filter predicates based on
 * timetable attributes, and populating derived fields before persistence.
 * </p>
 *
 * @author Krati Trivedi
 */
@Repository
public class TimetableDAOImpl extends BaseDAOImpl<TimetableDTO> implements TimetableDAOInt {

    /**
     * DAO for retrieving {@link SubjectDTO} data used to populate
     * the subject name on the timetable record before persistence.
     */
    @Autowired
    SubjectDAOInt subjectDAO;

    /**
     * DAO for retrieving {@link CourseDTO} data used to populate
     * the course name on the timetable record before persistence.
     */
    @Autowired
    CourseDAOInt courseDAO;

    /**
     * Returns the {@link Class} type of {@link TimetableDTO} managed by this DAO.
     *
     * @return {@code TimetableDTO.class}
     */
    @Override
    public Class<TimetableDTO> getDTOClass() {
        // TODO Auto-generated method stub
        return TimetableDTO.class;
    }

    /**
     * Builds and returns a list of JPA {@link Predicate} conditions for filtering
     * timetable records based on the non-empty fields of the given {@link TimetableDTO}.
     * <p>
     * The following fields are used for filtering when non-empty, each using a
     * prefix-based LIKE condition:
     * <ul>
     *   <li>{@code subjectName} — filters timetables whose subject name starts with the given value</li>
     *   <li>{@code courseName} — filters timetables whose course name starts with the given value</li>
     * </ul>
     * </p>
     *
     * @param dto             the {@link TimetableDTO} containing filter values
     * @param criteriaBuilder the {@link CriteriaBuilder} used to construct predicates
     * @param qRoot           the {@link Root} of the criteria query for {@link TimetableDTO}
     * @return a list of {@link Predicate} conditions for the WHERE clause
     */
    @Override
    public List<Predicate> getWhereClause(TimetableDTO dto, CriteriaBuilder criteriaBuilder,
            Root<TimetableDTO> qRoot) {

        List<Predicate> conditions = new ArrayList<Predicate>();

        if (!isEmptyString(dto.getSubjectName())) {
            conditions.add(criteriaBuilder.like(qRoot.get("subjectName"), dto.getSubjectName() + "%"));
        }
        if (!isEmptyString(dto.getCourseName())) {
            conditions.add(criteriaBuilder.like(qRoot.get("courseName"), dto.getCourseName() + "%"));
        }
        return conditions;
    }

    /**
     * Populates derived fields on the {@link TimetableDTO} before it is persisted.
     * <p>
     * If a valid {@code courseId} is present on the DTO, the corresponding
     * {@link CourseDTO} is retrieved and the course name is set on the timetable record.
     * If a valid {@code subjectId} is present, the corresponding {@link SubjectDTO}
     * is retrieved and the subject name is set on the timetable record.
     * </p>
     *
     * @param dto         the {@link TimetableDTO} to be populated
     * @param userContext the current user's context
     */
    @Override
    protected void populate(TimetableDTO dto, UserContext userContext) {
        if (dto.getCourseId() != null && dto.getCourseId() > 0) {
            CourseDTO courseDTO = courseDAO.findByPk(dto.getCourseId(), userContext);
            dto.setCourseName(courseDTO.getName());
        }
        if (dto.getSubjectId() != null && dto.getSubjectId() > 0) {
            SubjectDTO subjectDTO = subjectDAO.findByPk(dto.getSubjectId(), userContext);
            dto.setSubjectName(subjectDTO.getName());
        }
    }
}