package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.CourseDTO;

@Repository
public class CourseDAOImpl extends BaseDAOImpl<CourseDTO> implements CourseDAOInt {

	@Override
	public Class<CourseDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return CourseDTO.class;
	}

	@Override
	public List<Predicate> getWhereClause(CourseDTO dto, CriteriaBuilder criteriaBuilder, Root<CourseDTO> qRoot) {
		List<Predicate> conditions = new ArrayList<Predicate>();
		if (!isEmptyString(dto.getName())) {
			conditions.add(criteriaBuilder.like(qRoot.get("name"), dto.getName() + "%"));
		}
		if (!isEmptyString(dto.getDescription())) {
			conditions.add(criteriaBuilder.like(qRoot.get("description"), dto.getDescription() + "%"));
		}
		if (!isEmptyString(dto.getDuration())) {
			conditions.add(criteriaBuilder.like(qRoot.get("duration"), dto.getDuration() + "%"));
		}
		
		return conditions;
	}

}
