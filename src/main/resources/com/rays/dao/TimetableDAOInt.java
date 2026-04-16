package com.rays.dao;

import com.rays.common.BaseDAOInt;
import com.rays.dto.TimetableDTO;

/**
 * DAO interface for {@link TimetableDTO} in the ORS application.
 * <p>
 * Extends {@link BaseDAOInt} parameterized with {@link TimetableDTO} to inherit
 * standard data access operation contracts such as add, update, delete,
 * find, and search for the Timetable entity.
 * </p>
 *
 * @author Krati Trivedi
 */
public interface TimetableDAOInt extends BaseDAOInt<TimetableDTO> {
}