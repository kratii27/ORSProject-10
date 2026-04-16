package com.rays.dao;

import com.rays.common.BaseDAOInt;
import com.rays.dto.StudentDTO;

/**
 * DAO interface for {@link StudentDTO} in the ORS application.
 * <p>
 * Extends {@link BaseDAOInt} parameterized with {@link StudentDTO} to inherit
 * standard data access operation contracts such as add, update, delete,
 * find, and search for the Student entity.
 * </p>
 *
 * @author Krati Trivedi
 */
public interface StudentDAOInt extends BaseDAOInt<StudentDTO> {
}