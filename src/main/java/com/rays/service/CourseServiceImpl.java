package com.rays.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.CourseDAOInt;
import com.rays.dto.CourseDTO;

@Service
@Transactional
public class CourseServiceImpl extends BaseServiceImpl<CourseDTO, CourseDAOInt> implements CourseServiceInt{
	

}
