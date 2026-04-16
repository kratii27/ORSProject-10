package com.rays.dao;

import com.rays.common.BaseDAOInt;
import com.rays.dto.CourseDTO;

/**
 * DAO interface for {@link CourseDTO} in the ORS application.
 * <p>
 * Extends {@link BaseDAOInt} parameterized with {@link CourseDTO} to inherit
 * standard data access operation contracts such as add, update, delete,
 * find, and search for the Course entity.
 * </p>
 *
 * @author Krati Trivedi
 */
public interface CourseDAOInt extends BaseDAOInt<CourseDTO> {
}