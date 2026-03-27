package com.rays.common;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rays.exception.DuplicateRecordException;


public interface BaseServiceInt<T extends BaseDTO> {
	
	public void update(T dto, UserContext userContext);

	public long save(T dto, UserContext userContext);

	public void delete(long id, UserContext userContext);

	public T findByPk(long pk, UserContext userContext);

	public T findByUniqueKey(String attribute, Object value, UserContext userContext);

	public List<T> search(T dto, int pageNo, int pageSize, UserContext userContext);

	public List search(T dto, UserContext userContext);

}
