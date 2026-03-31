package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.RoleDAOint;
import com.rays.dto.RoleDTO;

/**
 * Service implementation class for managing role operations.
 * This class provides the business logic implementation for role-related
 * operations by extending BaseServiceImpl and implementing RoleServiceInt.
 * It handles role data persistence with transactional support.
 * 
 * @author Krati Trivedi
 */
@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<RoleDTO, RoleDAOint> implements RoleServiceInt{
	

}