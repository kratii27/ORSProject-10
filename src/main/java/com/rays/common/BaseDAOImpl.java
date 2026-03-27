package com.rays.common;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public abstract class BaseDAOImpl<T extends BaseDTO> implements BaseDAOInt<T> {

	@PersistenceContext
	protected EntityManager entityManager;

	public abstract Class<T> getDTOClass();

	public abstract List<Predicate> getWhereClause(T dto, CriteriaBuilder criteriaBuilder, Root<T> qRoot);

	protected void populate(T dto, UserContext userContext) {

	}

	@Override
	public long add(T dto, UserContext userContext) {
		dto.setCreatedBy(userContext.getLoginId());
		dto.setModifiedBy(userContext.getLoginId());
		dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		populate(dto, userContext);
		entityManager.persist(dto);
		return dto.getId();
	}

	@Override
	public void update(T dto, UserContext userContext) {
		dto.setModifiedBy(userContext.getLoginId());
		dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		populate(dto, userContext);
		entityManager.merge(dto);
	}

	@Override
	public void delete(T dto, UserContext userContext) {
		entityManager.remove(dto);
	}

	@Override
	public T findByPk(long pk, UserContext userContext) {
		T dto = entityManager.find(getDTOClass(), pk);
		return dto;
	}

	@Override
	public T findByUniqueKey(String attribute, Object value, UserContext userContext) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(getDTOClass());
		Root<T> qRoot = criteriaQuery.from(getDTOClass());
		Predicate condition = builder.equal(qRoot.get(attribute), value);
		criteriaQuery.where(condition);
		TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
		List<T> list = typedQuery.getResultList();
		T dto = null;
		if (list.size() == 1) {
			dto = list.get(0);
		}
		return dto;
	}

	@Override
	public List<T> search(T dto, int pageNo, int pageSize, UserContext userContext) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getDTOClass());
		Root<T> qRoot = criteriaQuery.from(getDTOClass());
		List<Predicate> conditions = getWhereClause(dto, criteriaBuilder, qRoot);

		for (Predicate condition : conditions) {
			criteriaQuery.where(condition);
		}

		TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
		
		if (pageSize > 0) {
			typedQuery.setFirstResult(pageNo * pageSize);
			typedQuery.setMaxResults(pageSize);
		}

		return typedQuery.getResultList();
	}

	@Override
	public List<T> search(T dto, UserContext userContext) {
		return search(dto, 0, 0, userContext);
	}

	protected boolean isEmptyString(String val) {
		return val == null || val.trim().length() == 0;
	}

	protected boolean isZeroNumber(Double val) {
		return val == null || val == 0;
	}

	protected boolean isZeroNumber(Long val) {
		return val == null || val == 0;
	}

	protected boolean isZeroNumber(Integer val) {
		return val == null || val == 0;
	}

	protected boolean isNotNull(Object val) {
		return val != null;
	}

}
