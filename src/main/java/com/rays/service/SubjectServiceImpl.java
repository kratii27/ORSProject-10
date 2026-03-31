package com.rays.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.SubjectDAOInt;
import com.rays.dto.SubjectDTO;

/**
 * Service implementation class for managing subject operations.
 * This class provides the business logic implementation for subject-related
 * operations by extending BaseServiceImpl and implementing SubjectServiceInt.
 * It handles subject data persistence with transactional support.
 * 
 * @author Krati Trivedi
 */
@Service
@Transactional
public class SubjectServiceImpl extends BaseServiceImpl<SubjectDTO, SubjectDAOInt> implements SubjectServiceInt {

}