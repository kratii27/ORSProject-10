package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.common.UserContext;
import com.rays.dto.MarksheetDTO;
import com.rays.dto.StudentDTO;

@Repository
public class MarksheetDAOImpl extends BaseDAOImpl<MarksheetDTO> implements MarksheetDAOInt {
	
	@Autowired
	StudentDAOInt studentDAO;

	@Override
	public Class<MarksheetDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return MarksheetDTO.class;
	}

	@Override
	public List<Predicate> getWhereClause(MarksheetDTO dto, CriteriaBuilder criteriaBuilder, Root<MarksheetDTO> qRoot) {
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		
		if (!isEmptyString(dto.getRollNo())) {
			conditions.add(criteriaBuilder.like(qRoot.get("rollNo"), dto.getRollNo() + "%"));
		}
		if (!isEmptyString(dto.getName())) {
			conditions.add(criteriaBuilder.like(qRoot.get("name"), dto.getName() + "%"));
		}
		if (!isZeroNumber(dto.getStudentId())) {
			conditions.add(criteriaBuilder.equal(qRoot.get("studentId"), dto.getStudentId()));
		}
		return conditions;
	}
	
	@Override
	protected void populate(MarksheetDTO dto, UserContext userContext) {
		if (dto.getStudentId() != null && dto.getStudentId() > 0) {
			StudentDTO studentDTO = studentDAO.findByPk(dto.getStudentId(), userContext);
			dto.setName(studentDTO.getFirstName() + " " + studentDTO.getLastName());
		}
	}


}
