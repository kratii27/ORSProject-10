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
import com.rays.dto.MarksheetDTO;
import com.rays.dto.StudentDTO;

/**
 * Data Access Object (DAO) implementation for {@link MarksheetDTO} in the ORS application.
 * <p>
 * Extends {@link BaseDAOImpl} to inherit common JPA-based CRUD operations and
 * implements {@link MarksheetDAOInt}. Provides entity-specific implementations for
 * retrieving the DTO class type, building dynamic search filter predicates based on
 * marksheet attributes, and populating derived fields before persistence.
 * </p>
 *
 * @author Krati Trivedi
 */
@Repository
public class MarksheetDAOImpl extends BaseDAOImpl<MarksheetDTO> implements MarksheetDAOInt {

    /**
     * DAO for retrieving {@link StudentDTO} data used to populate
     * the student name on the marksheet before persistence.
     */
    @Autowired
    StudentDAOInt studentDAO;

    /**
     * Returns the {@link Class} type of {@link MarksheetDTO} managed by this DAO.
     *
     * @return {@code MarksheetDTO.class}
     */
    @Override
    public Class<MarksheetDTO> getDTOClass() {
        // TODO Auto-generated method stub
        return MarksheetDTO.class;
    }

    /**
     * Builds and returns a list of JPA {@link Predicate} conditions for filtering
     * marksheet records based on the non-empty fields of the given {@link MarksheetDTO}.
     * <p>
     * The following fields are used for filtering when non-empty or non-zero:
     * <ul>
     *   <li>{@code rollNo} — filters marksheets whose roll number starts with the given value</li>
     *   <li>{@code name} — filters marksheets whose student name starts with the given value</li>
     *   <li>{@code studentId} — filters marksheets by exact student ID match</li>
     * </ul>
     * </p>
     *
     * @param dto             the {@link MarksheetDTO} containing filter values
     * @param criteriaBuilder the {@link CriteriaBuilder} used to construct predicates
     * @param qRoot           the {@link Root} of the criteria query for {@link MarksheetDTO}
     * @return a list of {@link Predicate} conditions for the WHERE clause
     */
    @Override
    public List<Predicate> getWhereClause(MarksheetDTO dto, CriteriaBuilder criteriaBuilder,
            Root<MarksheetDTO> qRoot) {

        List<Predicate> conditions = new ArrayList<Predicate>();

        if (!isEmptyString(dto.getRollNo())) {
            conditions.add(criteriaBuilder.like(qRoot.get("rollNo"), dto.getRollNo() + "%"));
        }
        if (!isEmptyString(dto.getName())) {
            conditions.add(criteriaBuilder.like(qRoot.get("name"), dto.getName() + "%"));
        }
        if (!isZeroNumber(dto.getStudentId())) {
            conditions.add(criteriaBuilder.equal(qRoot.get("studentId"), dto.getStudentId()));
        }
        return conditions;
    }

    /**
     * Populates derived fields on the {@link MarksheetDTO} before it is persisted.
     * <p>
     * If a valid {@code studentId} is present on the DTO, the corresponding
     * {@link StudentDTO} is retrieved and the student's full name
     * (first name + last name) is set on the marksheet.
     * </p>
     *
     * @param dto         the {@link MarksheetDTO} to be populated
     * @param userContext the current user's context
     */
    @Override
    protected void populate(MarksheetDTO dto, UserContext userContext) {
        if (dto.getStudentId() != null && dto.getStudentId() > 0) {
            StudentDTO studentDTO = studentDAO.findByPk(dto.getStudentId(), userContext);
            dto.setName(studentDTO.getFirstName() + " " + studentDTO.getLastName());
        }
    }

	@Override
	public List<MarksheetDTO> getMeritList() {
		System.out.println("marksheetDao merit marksheett run start");
//		List list = super.runHQL("from MarksheetDTO order by (physics+chemistry+maths) desc limit 0,10", null);
			List list = super.marksheetMeritList("from MarksheetDTO order by (physics+chemistry+maths) desc", null);
		return list;
	}
}