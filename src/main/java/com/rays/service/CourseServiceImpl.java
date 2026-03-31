package com.rays.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.CourseDAOInt;
import com.rays.dto.CourseDTO;

/**
 * Service implementation class for managing course operations.
 * This class provides the business logic implementation for course-related
 * operations by extending BaseServiceImpl and implementing CourseServiceInt.
 * It handles course data persistence with transactional support.
 * 
 * @author Krati Trivedi
 */
@Service
@Transactional
public class CourseServiceImpl extends BaseServiceImpl<CourseDTO, CourseDAOInt> implements CourseServiceInt{
	

}