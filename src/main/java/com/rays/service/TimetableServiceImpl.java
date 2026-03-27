package com.rays.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.TimetableDAOInt;
import com.rays.dto.TimetableDTO;

@Service
@Transactional
public class TimetableServiceImpl extends BaseServiceImpl<TimetableDTO, TimetableDAOInt> implements TimetableServiceInt {

}
