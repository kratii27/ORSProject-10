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

@Repository
public class StudentDAOImpl extends BaseDAOImpl<StudentDTO> implements StudentDAOInt {
	
	@Autowired
	CollegeDAOInt collegeDao;

	@Override
	public Class<StudentDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return StudentDTO.class;
	}

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
	
	@Override
	protected void populate(StudentDTO dto, UserContext userContext) {
		if (dto.getCollegeId() != null && dto.getCollegeId() > 0) {
			CollegeDTO collegeDTO = collegeDao.findByPk(dto.getCollegeId(), userContext);
			dto.setCollegeName(collegeDTO.getName());
		}
	}

}
