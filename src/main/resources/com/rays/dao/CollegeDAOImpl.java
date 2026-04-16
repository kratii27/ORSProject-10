package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.CollegeDTO;

/**
 * Data Access Object (DAO) implementation for {@link CollegeDTO} in the ORS application.
 * <p>
 * Extends {@link BaseDAOImpl} to inherit common JPA-based CRUD operations and
 * implements {@link CollegeDAOInt}. Provides entity-specific implementations for
 * retrieving the DTO class type and building dynamic search filter predicates
 * based on college attributes.
 * </p>
 *
 * @author Krati Trivedi
 */
@Repository
public class CollegeDAOImpl extends BaseDAOImpl<CollegeDTO> implements CollegeDAOInt {

    /**
     * Returns the {@link Class} type of {@link CollegeDTO} managed by this DAO.
     *
     * @return {@code CollegeDTO.class}
     */
    @Override
    public Class<CollegeDTO> getDTOClass() {
        // TODO Auto-generated method stub
        return CollegeDTO.class;
    }

    /**
     * Builds and returns a list of JPA {@link Predicate} conditions for filtering
     * college records based on the non-empty fields of the given {@link CollegeDTO}.
     * <p>
     * The following fields are used for filtering when non-empty, each using a
     * prefix-based LIKE condition:
     * <ul>
     *   <li>{@code name} — filters colleges whose name starts with the given value</li>
     *   <li>{@code address} — filters colleges whose address starts with the given value</li>
     *   <li>{@code city} — filters colleges whose city starts with the given value</li>
     *   <li>{@code state} — filters colleges whose state starts with the given value</li>
     *   <li>{@code phoneNo} — filters colleges whose phone number starts with the given value</li>
     * </ul>
     * </p>
     *
     * @param dto             the {@link CollegeDTO} containing filter values
     * @param criteriaBuilder the {@link CriteriaBuilder} used to construct predicates
     * @param qRoot           the {@link Root} of the criteria query for {@link CollegeDTO}
     * @return a list of {@link Predicate} conditions for the WHERE clause
     */
    @Override
    public List<Predicate> getWhereClause(CollegeDTO dto, CriteriaBuilder criteriaBuilder, Root<CollegeDTO> qRoot) {
        List<Predicate> conditions = new ArrayList<Predicate>();

        if (!isEmptyString(dto.getName())) {
            conditions.add(criteriaBuilder.like(qRoot.get("name"), dto.getName() + "%"));
        }
        if (!isEmptyString(dto.getAddress())) {
            conditions.add(criteriaBuilder.like(qRoot.get("address"), dto.getAddress() + "%"));
        }
        if (!isEmptyString(dto.getCity())) {
            conditions.add(criteriaBuilder.like(qRoot.get("city"), dto.getCity() + "%"));
        }
        if (!isEmptyString(dto.getState())) {
            conditions.add(criteriaBuilder.like(qRoot.get("state"), dto.getState() + "%"));
        }
        if (!isEmptyString(dto.getPhoneNo())) {
            conditions.add(criteriaBuilder.like(qRoot.get("phoneNo"), dto.getPhoneNo() + "%"));
        }
        return conditions;
    }
}