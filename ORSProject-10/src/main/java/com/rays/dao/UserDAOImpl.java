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
import com.rays.dto.RoleDTO;
import com.rays.dto.UserDTO;

@Repository
public class UserDAOImpl extends BaseDAOImpl<UserDTO> implements UserDAOInt {
	
	@Autowired
	RoleDAOint roleDao;

	@Override
	public Class<UserDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return UserDTO.class;
	}

	@Override
	public List<Predicate> getWhereClause(UserDTO dto, CriteriaBuilder criteriaBuilder, Root<UserDTO> qRoot) {
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		
		if (!isEmptyString(dto.getFirstName())) {
			conditions.add(criteriaBuilder.like(qRoot.get("firstName"), dto.getFirstName() + "%"));
		}
		if (!isEmptyString(dto.getLastName())) {
			conditions.add(criteriaBuilder.like(qRoot.get("lastName"), dto.getLastName() + "%"));
		}
		if (!isEmptyString(dto.getLogin())) {
			conditions.add(criteriaBuilder.like(qRoot.get("login"), dto.getLogin() + "%"));
		}
		if (!isZeroNumber(dto.getRoleId())) {
			conditions.add(criteriaBuilder.equal(qRoot.get("roleId"), dto.getRoleId()));
		}
		if (isNotNull(dto.getDob())) {
			conditions.add(criteriaBuilder.equal(qRoot.get("dob"), dto.getDob()));
		}
		if (!isEmptyString(dto.getStatus())) {
			conditions.add(criteriaBuilder.like(qRoot.get("status"), dto.getStatus()));
		}
		return conditions;
	}
	
	@Override
	protected void populate(UserDTO dto, UserContext userContext) {

	    if (dto.getRoleId() != null && dto.getRoleId() > 0) {
	        RoleDTO roleDTO = roleDao.findByPk(dto.getRoleId(), userContext);
	        if (roleDTO != null) {
	            dto.setRoleName(roleDTO.getName());
	        }
	    }

	    if (dto.getId() != null && dto.getId() > 0) {
	        UserDTO user = findByPk(dto.getId(), userContext);
	        if (user != null) {
	            dto.setLastLogin(user.getLastLogin());
	        }
	    }

	    if (dto.getId() != null && dto.getId() > 0) {
	        UserDTO userData = findByPk(dto.getId(), null);
	        if (userData != null) {
	            dto.setImageId(userData.getImageId());
	        }
	    }
	}

}
