package com.rays.dao;

import com.rays.common.BaseDAOInt;
import com.rays.dto.FacultyDTO;

/**
 * DAO interface for {@link FacultyDTO} in the ORS application.
 * <p>
 * Extends {@link BaseDAOInt} parameterized with {@link FacultyDTO} to inherit
 * standard data access operation contracts such as add, update, delete,
 * find, and search for the Faculty entity.
 * </p>
 *
 * @author Krati Trivedi
 */
public interface FacultyDAOInt extends BaseDAOInt<FacultyDTO> {
}