package com.rays.dao;

import com.rays.common.BaseDAOInt;
import com.rays.common.UserContext;
import com.rays.dto.RoleDTO;

/**
 * DAO interface for {@link RoleDTO} in the ORS application.
 * <p>
 * Extends {@link BaseDAOInt} parameterized with {@link RoleDTO} to inherit
 * standard data access operation contracts such as add, update, delete,
 * find, and search for the Role entity.
 * </p>
 *
 * @author Krati Trivedi
 */
public interface RoleDAOint extends BaseDAOInt<RoleDTO> {
}