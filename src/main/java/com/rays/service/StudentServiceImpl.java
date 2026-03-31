package com.rays.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.StudentDAOInt;
import com.rays.dto.StudentDTO;

/**
 * Service implementation class for managing student operations.
 * This class provides the business logic implementation for student-related
 * operations by extending BaseServiceImpl and implementing StudentServiceInt.
 * It handles student data persistence with transactional support.
 * 
 * @author Krati Trivedi
 */
@Service
@Transactional
public class StudentServiceImpl extends BaseServiceImpl<StudentDTO, StudentDAOInt> implements StudentServiceInt {

}