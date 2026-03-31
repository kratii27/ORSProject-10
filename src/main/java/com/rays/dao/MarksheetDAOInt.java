package com.rays.dao;

import java.util.List;

import com.rays.common.BaseDAOInt;
import com.rays.dto.MarksheetDTO;

/**
 * DAO interface for {@link MarksheetDTO} in the ORS application.
 * <p>
 * Extends {@link BaseDAOInt} parameterized with {@link MarksheetDTO} to inherit
 * standard data access operation contracts such as add, update, delete,
 * find, and search for the Marksheet entity.
 * </p>
 *
 * @author Krati Trivedi
 */
public interface MarksheetDAOInt extends BaseDAOInt<MarksheetDTO> {
	
	public List<MarksheetDTO> getMeritList();
	
}