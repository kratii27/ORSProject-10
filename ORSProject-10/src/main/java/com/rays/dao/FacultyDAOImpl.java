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

@Repository
public class FacultyDAOImpl extends BaseDAOImpl<FacultyDTO> implements FacultyDAOInt {

	@Autowired
	CollegeDAOInt collegeDAO;

	@Autowired
	CourseDAOInt courseDAO;

	@Autowired
	SubjectDAOInt subjectDAO;

	@Override
	public Class<FacultyDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return FacultyDTO.class;
	}

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
