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
import com.rays.dto.CollegeDTO;
import com.rays.dto.CourseDTO;
import com.rays.dto.FacultyDTO;
import com.rays.dto.SubjectDTO;

/**
 * Data Access Object (DAO) implementation for {@link FacultyDTO} in the ORS application.
 * <p>
 * Extends {@link BaseDAOImpl} to inherit common JPA-based CRUD operations and
 * implements {@link FacultyDAOInt}. Provides entity-specific implementations for
 * retrieving the DTO class type, building dynamic search filter predicates,
 * and populating denormalized fields such as college name, course name,
 * and subject name before persistence.
 * </p>
 *
 * @author Krati Trivedi
 */
@Repository
public class FacultyDAOImpl extends BaseDAOImpl<FacultyDTO> implements FacultyDAOInt {

    /**
     * DAO for retrieving College data used to populate the college name
     * on the {@link FacultyDTO} before persistence.
     */
    @Autowired
    CollegeDAOInt collegeDAO;

    /**
     * DAO for retrieving Course data used to populate the course name
     * on the {@link FacultyDTO} before persistence.
     */
    @Autowired
    CourseDAOInt courseDAO;

    /**
     * DAO for retrieving Subject data used to populate the subject name
     * on the {@link FacultyDTO} before persistence.
     */
    @Autowired
    SubjectDAOInt subjectDAO;

    /**
     * Returns the {@link Class} type of {@link FacultyDTO} managed by this DAO.
     *
     * @return {@code FacultyDTO.class}
     */
    @Override
    public Class<FacultyDTO> getDTOClass() {
        // TODO Auto-generated method stub
        return FacultyDTO.class;
    }

    /**
     * Builds and returns a list of JPA {@link Predicate} conditions for filtering
     * faculty records based on the non-empty fields of the given {@link FacultyDTO}.
     * <p>
     * The following fields are used for filtering when non-empty, each using a
     * prefix-based LIKE condition:
     * <ul>
     *   <li>{@code firstName} — filters faculty whose first name starts with the given value</li>
     *   <li>{@code email} — filters faculty whose email starts with the given value</li>
     *   <li>{@code collegeName} — filters faculty whose college name starts with the given value</li>
     *   <li>{@code courseName} — filters faculty whose course name starts with the given value</li>
     *   <li>{@code subjectName} — filters faculty whose subject name starts with the given value</li>
     * </ul>
     * </p>
     *
     * @param dto             the {@link FacultyDTO} containing filter values
     * @param criteriaBuilder the {@link CriteriaBuilder} used to construct predicates
     * @param qRoot           the {@link Root} of the criteria query for {@link FacultyDTO}
     * @return a list of {@link Predicate} conditions for the WHERE clause
     */
    @Override
    public List<Predicate> getWhereClause(FacultyDTO dto, CriteriaBuilder criteriaBuilder, Root<FacultyDTO> qRoot) {
        List<Predicate> conditions = new ArrayList<Predicate>();

        if (!isEmptyString(dto.getFirstName())) {
            conditions.add(criteriaBuilder.like(qRoot.get("firstName"), dto.getFirstName() + "%"));
        }
        if (!isEmptyString(dto.getEmail())) {
            conditions.add(criteriaBuilder.like(qRoot.get("email"), dto.getEmail() + "%"));
        }
        if (!isEmptyString(dto.getCollegeName())) {
            conditions.add(criteriaBuilder.like(qRoot.get("collegeName"), dto.getCollegeName() + "%"));
        }
        if (!isEmptyString(dto.getCourseName())) {
            conditions.add(criteriaBuilder.like(qRoot.get("courseName"), dto.getCourseName() + "%"));
        }
        if (!isEmptyString(dto.getSubjectName())) {
            conditions.add(criteriaBuilder.like(qRoot.get("subjectName"), dto.getSubjectName() + "%"));
        }
        return conditions;
    }

    /**
     * Populates denormalized name fields on the {@link FacultyDTO} before persistence.
     * <p>
     * Resolves and sets the following fields based on their corresponding IDs:
     * <ul>
     *   <li>{@code collegeName} — resolved from {@code collegeId} using {@link CollegeDAOInt}</li>
     *   <li>{@code subjectName} — resolved from {@code subjectId} using {@link SubjectDAOInt}</li>
     *   <li>{@code courseName} — resolved from {@code courseId} using {@link CourseDAOInt}</li>
     * </ul>
     * Each field is only populated if its corresponding ID is non-null and greater than zero.
     * </p>
     *
     * @param dto         the {@link FacultyDTO} to be populated
     * @param userContext the current user's context
     */
    @Override
    protected void populate(FacultyDTO dto, UserContext userContext) {

        if (dto.getCollegeId() != null && dto.getCollegeId() > 0) {
            CollegeDTO collegeDTO = collegeDAO.findByPk(dto.getCollegeId(), userContext);
            dto.setCollegeName(collegeDTO.getName());
        }

        if (dto.getSubjectId() != null && dto.getSubjectId() > 0) {
            SubjectDTO subjectDTO = subjectDAO.findByPk(dto.getSubjectId(), userContext);
            dto.setSubjectName(subjectDTO.getName());
        }

        if (dto.getCourseId() != null && dto.getCourseId() > 0) {
            CourseDTO courseDTO = courseDAO.findByPk(dto.getCourseId(), userContext);
            dto.setCourseName(courseDTO.getName());
        }
    }
}