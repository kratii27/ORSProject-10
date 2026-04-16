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
import com.rays.dto.StudentDTO;

/**
 * Data Access Object (DAO) implementation for {@link StudentDTO} in the ORS application.
 * <p>
 * Extends {@link BaseDAOImpl} to inherit common JPA-based CRUD operations and
 * implements {@link StudentDAOInt}. Provides entity-specific implementations for
 * retrieving the DTO class type, building dynamic search filter predicates based on
 * student attributes, and populating derived fields before persistence.
 * </p>
 *
 * @author Krati Trivedi
 */
@Repository
public class StudentDAOImpl extends BaseDAOImpl<StudentDTO> implements StudentDAOInt {

    /**
     * DAO for retrieving {@link CollegeDTO} data used to populate
     * the college name on the student record before persistence.
     */
    @Autowired
    CollegeDAOInt collegeDao;

    /**
     * Returns the {@link Class} type of {@link StudentDTO} managed by this DAO.
     *
     * @return {@code StudentDTO.class}
     */
    @Override
    public Class<StudentDTO> getDTOClass() {
        // TODO Auto-generated method stub
        return StudentDTO.class;
    }

    /**
     * Builds and returns a list of JPA {@link Predicate} conditions for filtering
     * student records based on the non-empty or non-null fields of the given {@link StudentDTO}.
     * <p>
     * The following fields are used for filtering when non-empty or non-null:
     * <ul>
     *   <li>{@code enrollmentNo} — filters students whose enrollment number starts with the given value</li>
     *   <li>{@code firstName} — filters students whose first name starts with the given value</li>
     *   <li>{@code collegeName} — filters students whose college name starts with the given value</li>
     *   <li>{@code email} — filters students whose email starts with the given value</li>
     *   <li>{@code dob} — filters students by exact date of birth match</li>
     *   <li>{@code phoneNo} — filters students whose phone number starts with the given value</li>
     * </ul>
     * </p>
     *
     * @param dto             the {@link StudentDTO} containing filter values
     * @param criteriaBuilder the {@link CriteriaBuilder} used to construct predicates
     * @param qRoot           the {@link Root} of the criteria query for {@link StudentDTO}
     * @return a list of {@link Predicate} conditions for the WHERE clause
     */
    @Override
    public List<Predicate> getWhereClause(StudentDTO dto, CriteriaBuilder criteriaBuilder, Root<StudentDTO> qRoot) {
        List<Predicate> conditions = new ArrayList<Predicate>();

        if (!isEmptyString(dto.getEnrollmentNo())) {
            conditions.add(criteriaBuilder.like(qRoot.get("enrollmentNo"), dto.getEnrollmentNo() + "%"));
        }
        if (!isEmptyString(dto.getFirstName())) {
            conditions.add(criteriaBuilder.like(qRoot.get("firstName"), dto.getFirstName() + "%"));
        }
        if (!isEmptyString(dto.getCollegeName())) {
            conditions.add(criteriaBuilder.like(qRoot.get("collegeName"), dto.getCollegeName() + "%"));
        }
        if (!isEmptyString(dto.getEmail())) {
            conditions.add(criteriaBuilder.like(qRoot.get("email"), dto.getEmail() + "%"));
        }
        if (isNotNull(dto.getDob())) {
            conditions.add(criteriaBuilder.equal(qRoot.get("dob"), dto.getDob()));
        }
        if (!isEmptyString(dto.getPhoneNo())) {
            conditions.add(criteriaBuilder.like(qRoot.get("phoneNo"), dto.getPhoneNo() + "%"));
        }
        return conditions;
    }

    /**
     * Populates derived fields on the {@link StudentDTO} before it is persisted.
     * <p>
     * If a valid {@code collegeId} is present on the DTO, the corresponding
     * {@link CollegeDTO} is retrieved and the college name is set on the student record.
     * </p>
     *
     * @param dto         the {@link StudentDTO} to be populated
     * @param userContext the current user's context
     */
    @Override
    protected void populate(StudentDTO dto, UserContext userContext) {
        if (dto.getCollegeId() != null && dto.getCollegeId() > 0) {
            CollegeDTO collegeDTO = collegeDao.findByPk(dto.getCollegeId(), userContext);
            dto.setCollegeName(collegeDTO.getName());
        }
    }
}