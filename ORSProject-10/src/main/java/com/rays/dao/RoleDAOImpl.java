package com.rays.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.common.UserContext;
import com.rays.dto.RoleDTO;

@Repository
public class RoleDAOImpl extends BaseDAOImpl<RoleDTO> implements RoleDAOint {
	

	@Override
	public Class<RoleDTO> getDTOClass() {
		return RoleDTO.class;
	}

	@Override
	public List<Predicate> getWhereClause(RoleDTO dto, CriteriaBuilder criteriaBuilder, Root<RoleDTO> qRoot) {
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		System.out.println(dto);
		
		if(dto != null) {
			if (!isZeroNumber(dto.getId())) {
				conditions.add(criteriaBuilder.equal(qRoot.get("id"), dto.getId()));
			}
			if (!isEmptyString(dto.getName())) {
				conditions.add(criteriaBuilder.like(qRoot.get("name"), dto.getName() + "%"));
			}
			if (!isEmptyString(dto.getDescription())) {
				conditions.add(criteriaBuilder.like(qRoot.get("description"), dto.getDescription() + "%"));
			}
		}
		System.out.println(conditions.toString());
		return conditions;
	}
	
	
}
