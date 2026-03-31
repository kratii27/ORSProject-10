package com.rays.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.CollegeDAOInt;
import com.rays.dto.CollegeDTO;

/**
 * Service implementation class for managing college operations.
 * This class provides the business logic implementation for college-related
 * operations by extending BaseServiceImpl and implementing CollegeServiceInt.
 * It handles college data persistence with transactional support.
 * 
 * @author Krati Trivedi
 */
@Service
@Transactional
public class CollegeServiceImpl extends BaseServiceImpl<CollegeDTO, CollegeDAOInt> implements CollegeServiceInt {

}