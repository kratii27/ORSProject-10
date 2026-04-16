package com.rays.dao;

import com.rays.common.BaseDAOInt;
import com.rays.dto.SubjectDTO;

/**
 * DAO interface for {@link SubjectDTO} in the ORS application.
 * <p>
 * Extends {@link BaseDAOInt} parameterized with {@link SubjectDTO} to inherit
 * standard data access operation contracts such as add, update, delete,
 * find, and search for the Subject entity.
 * </p>
 *
 * @author Krati Trivedi
 */
public interface SubjectDAOInt extends BaseDAOInt<SubjectDTO> {
}