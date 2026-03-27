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

@Repository
public class TimetableDAOImpl extends BaseDAOImpl<TimetableDTO> implements TimetableDAOInt {
	
	@Autowired
	SubjectDAOInt subjectDAO;

	@Autowired
	CourseDAOInt courseDAO;


	@Override
	public Class<TimetableDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return TimetableDTO.class;
	}

	@Override
	public List<Predicate> getWhereClause(TimetableDTO dto, CriteriaBuilder criteriaBuilder, Root<TimetableDTO> qRoot) {
		
		List<Predicate> conditions = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getSubjectName())) {
			conditions.add(criteriaBuilder.like(qRoot.get("subjectName"), dto.getSubjectName() + "%"));
		}
		if (!isEmptyString(dto.getCourseName())) {
			conditions.add(criteriaBuilder.like(qRoot.get("courseName"), dto.getCourseName() + "%"));
		}
		return conditions;
	}
	
	@Override
	protected void populate(TimetableDTO dto, UserContext userContext) {
		if (dto.getCourseId() != null && dto.getCourseId() > 0) {
			CourseDTO courseDTO = courseDAO.findByPk(dto.getCourseId(), userContext);
			dto.setCourseName(courseDTO.getName());
		}
		if (dto.getSubjectId() != null && dto.getSubjectId()  > 0) {
			SubjectDTO subjectDTO = subjectDAO.findByPk(dto.getSubjectId(), userContext);
			dto.setSubjectName(subjectDTO.getName());
		}
	}

}
