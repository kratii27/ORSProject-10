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
import com.rays.dto.FeeStructureDTO;
import com.rays.dto.UserDTO;

@Repository
public class FeeStructureDAOImpl extends BaseDAOImpl<FeeStructureDTO> implements FeeStructureDAOInt {
	
	@Autowired
	CourseDAOInt courseDao;
	
	@Override
	public Class<FeeStructureDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return FeeStructureDTO.class;
	}
	
	@Override
	public List<Predicate> getWhereClause(FeeStructureDTO dto, CriteriaBuilder criteriaBuilder, Root<FeeStructureDTO> qRoot) {
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		
		if (!isZeroNumber(dto.getCourseId())) {
			conditions.add(criteriaBuilder.equal(qRoot.get("courseId"), dto.getCourseId()));
		}
		if (!isEmptyString(dto.getCourseName())) {
			conditions.add(criteriaBuilder.like(qRoot.get("courseName"), dto.getCourseName() + "%"));
		}
		if (!isZeroNumber(dto.getBaseFee())) {
			conditions.add(criteriaBuilder.equal(qRoot.get("baseFee"), dto.getBaseFee()));
		}
		if (!isZeroNumber(dto.getExamFee())) {
			conditions.add(criteriaBuilder.equal(qRoot.get("examFee"), dto.getExamFee()));
		}
		if (isNotNull(dto.getSubmissionDate())) {
			conditions.add(criteriaBuilder.equal(qRoot.get("submissionDate"), dto.getSubmissionDate()));
		}
		
		return conditions;
	}
	
	@Override
	protected void populate(FeeStructureDTO dto, UserContext userContext) {
		if (dto.getCourseId() != null && dto.getCourseId() > 0) {
			CourseDTO courseDTO = courseDao.findByPk(dto.getCourseId(), userContext);
			dto.setCourseName(courseDTO.getName());
		}
	}
}