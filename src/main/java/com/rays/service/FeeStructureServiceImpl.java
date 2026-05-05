package com.rays.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.FeeStructureDAOInt;
import com.rays.dto.FeeStructureDTO;

@Service
@Transactional
public class FeeStructureServiceImpl extends BaseServiceImpl<FeeStructureDTO, FeeStructureDAOInt> implements FeeStructureServiceInt {
	
	
	
}