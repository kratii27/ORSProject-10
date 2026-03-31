package com.rays.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.FacultyDAOInt;
import com.rays.dto.FacultyDTO;

/**
 * Service implementation class for managing faculty operations.
 * This class provides the business logic implementation for faculty-related
 * operations by extending BaseServiceImpl and implementing FacultyServiceInt.
 * It handles faculty data persistence with transactional support.
 * 
 * @author Krati Trivedi
 */
@Service
@Transactional
public class FacultyServiceImpl extends BaseServiceImpl<FacultyDTO, FacultyDAOInt> implements FacultyServiceInt {

}