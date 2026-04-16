package com.rays.dao;

import com.rays.common.BaseDAOInt;
import com.rays.dto.UserDTO;

/**
 * DAO interface for {@link UserDTO} in the ORS application.
 * <p>
 * Extends {@link BaseDAOInt} parameterized with {@link UserDTO} to inherit
 * standard data access operation contracts such as add, update, delete,
 * find, and search for the User entity.
 * </p>
 *
 * @author Krati Trivedi
 */
public interface UserDAOInt extends BaseDAOInt<UserDTO> {
}