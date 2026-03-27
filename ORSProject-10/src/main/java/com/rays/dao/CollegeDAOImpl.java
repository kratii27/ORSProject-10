package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.CollegeDTO;

@Repository
public class CollegeDAOImpl extends BaseDAOImpl<CollegeDTO> implements CollegeDAOInt {

	@Override
	public Class<CollegeDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return CollegeDTO.class;
	}

	@Override
	public List<Predicate> getWhereClause(CollegeDTO dto, CriteriaBuilder criteriaBuilder, Root<CollegeDTO> qRoot) {
		List<Predicate> conditions = new ArrayList<Predicate>();
		
		if (!isEmptyString(dto.getName())) {
			conditions.add(criteriaBuilder.like(qRoot.get("name"), dto.getName() + "%"));
		}
		if (!isEmptyString(dto.getAddress())) {
			conditions.add(criteriaBuilder.like(qRoot.get("address"), dto.getAddress() + "%"));
		}
		if (!isEmptyString(dto.getCity())) {
			conditions.add(criteriaBuilder.like(qRoot.get("city"), dto.getCity() + "%"));
		}
		if (!isEmptyString(dto.getState())) {
			conditions.add(criteriaBuilder.like(qRoot.get("state"), dto.getState() + "%"));
		}
		if (!isEmptyString(dto.getPhoneNo())) {
			conditions.add(criteriaBuilder.like(qRoot.get("phoneNo"), dto.getPhoneNo() + "%"));
		}
		return conditions;
	}

}
