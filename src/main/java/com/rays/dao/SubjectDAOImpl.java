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

@Repository
public class SubjectDAOImpl extends BaseDAOImpl<SubjectDTO> implements SubjectDAOInt {
	
	@Autowired
	CourseDAOInt courseDao;

	@Override
	public Class<SubjectDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return SubjectDTO.class;
	}

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
	
	@Override
	protected void populate(SubjectDTO dto, UserContext userContext) {
		if (dto.getCourseId() != null && dto.getCourseId() > 0) {
			CourseDTO courseDTO = courseDao.findByPk(dto.getCourseId(), userContext);
			dto.setCourseName(courseDTO.getName());
		}
	}

}
