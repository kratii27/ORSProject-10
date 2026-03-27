package com.rays.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.MarksheetDAOInt;
import com.rays.dto.MarksheetDTO;

@Service
@Transactional
public class MarksheetServiceImpl extends BaseServiceImpl<MarksheetDTO, MarksheetDAOInt> implements MarksheetServiceInt {

}
