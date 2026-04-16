package com.rays.dao;

import com.rays.common.BaseDAOInt;
import com.rays.dto.CollegeDTO;

/**
 * DAO interface for {@link CollegeDTO} in the ORS application.
 * <p>
 * Extends {@link BaseDAOInt} parameterized with {@link CollegeDTO} to inherit
 * standard data access operation contracts such as add, update, delete,
 * find, and search for the College entity.
 * </p>
 *
 * @author Krati Trivedi
 */
public interface CollegeDAOInt extends BaseDAOInt<CollegeDTO> {
}