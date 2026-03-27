package com.rays.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.exception.DatabaseException;


@Transactional
public class BaseServiceImpl<T extends BaseDTO, D extends BaseDAOInt<T>> implements BaseServiceInt<T> {

	@Autowired
	protected D dao;
	

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(T dto, UserContext userContext) {
		T existDto = findByPk(dto.getId(), userContext);
		System.out.println("before find by pk" + existDto.toString());
		if (existDto != null) {
			dto.setCreatedBy(existDto.getCreatedBy());
			dto.setCreatedDateTime(existDto.getCreatedDateTime());
		}
		dao.update(dto, userContext);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public long save(T dto, UserContext userContext)  {
		Long id = dto.getId();
		if (id != null && id > 0) {
		    update(dto, userContext);
		} else {
			id = dao.add(dto, userContext);
		}
		return id;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id, UserContext userContext) {
		T dto = dao.findByPk(id, userContext);
		if (dto != null) {
			dao.delete(dto, userContext);
		}
	}

	@Transactional(readOnly = true)
	public T findByPk(long pk, UserContext userContext) {
		T dto = dao.findByPk(pk, userContext);
		return dto;
	}

	@Transactional(readOnly = true)
	public T findByUniqueKey(String attribute, Object value, UserContext userContext) {
		return dao.findByUniqueKey(attribute, value, userContext);
	}

	@Transactional(readOnly = true)
	public List<T> search(T dto, int pageNo, int pageSize, UserContext userContext) {
		return dao.search(dto, pageNo, pageSize, userContext);
	}

	@Transactional(readOnly = true)
	public List<T> search(T dto, UserContext userContext) {
		return dao.search(dto, userContext);
	}

	

}
