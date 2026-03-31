package com.rays.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.TimetableDAOInt;
import com.rays.dto.TimetableDTO;

/**
 * Service implementation class for managing timetable operations.
 * This class provides the business logic implementation for timetable-related
 * operations by extending BaseServiceImpl and implementing TimetableServiceInt.
 * It handles timetable data persistence with transactional support.
 * 
 * @author Krati Trivedi
 */
@Service
@Transactional
public class TimetableServiceImpl extends BaseServiceImpl<TimetableDTO, TimetableDAOInt> implements TimetableServiceInt {

}