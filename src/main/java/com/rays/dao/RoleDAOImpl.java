package com.rays.dao;

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

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.common.UserContext;
import com.rays.dto.RoleDTO;

/**
 * Data Access Object (DAO) implementation for {@link RoleDTO} in the ORS application.
 * <p>
 * Extends {@link BaseDAOImpl} to inherit common JPA-based CRUD operations and
 * implements {@link RoleDAOint}. Provides entity-specific implementations for
 * retrieving the DTO class type and building dynamic search filter predicates
 * based on role attributes.
 * </p>
 *
 * @author Krati Trivedi
 */
@Repository
public class RoleDAOImpl extends BaseDAOImpl<RoleDTO> implements RoleDAOint {

    /**
     * Returns the {@link Class} type of {@link RoleDTO} managed by this DAO.
     *
     * @return {@code RoleDTO.class}
     */
    @Override
    public Class<RoleDTO> getDTOClass() {
        return RoleDTO.class;
    }

    /**
     * Builds and returns a list of JPA {@link Predicate} conditions for filtering
     * role records based on the non-empty or non-zero fields of the given {@link RoleDTO}.
     * <p>
     * If the provided DTO is non-null, the following fields are used for filtering:
     * <ul>
     *   <li>{@code id} — filters roles by exact ID match when non-zero</li>
     *   <li>{@code name} — filters roles whose name starts with the given value</li>
     *   <li>{@code description} — filters roles whose description starts with the given value</li>
     * </ul>
     * </p>
     *
     * @param dto             the {@link RoleDTO} containing filter values
     * @param criteriaBuilder the {@link CriteriaBuilder} used to construct predicates
     * @param qRoot           the {@link Root} of the criteria query for {@link RoleDTO}
     * @return a list of {@link Predicate} conditions for the WHERE clause
     */
    @Override
    public List<Predicate> getWhereClause(RoleDTO dto, CriteriaBuilder criteriaBuilder, Root<RoleDTO> qRoot) {

        List<Predicate> conditions = new ArrayList<Predicate>();
        System.out.println(dto);

        if (dto != null) {
            if (!isZeroNumber(dto.getId())) {
                conditions.add(criteriaBuilder.equal(qRoot.get("id"), dto.getId()));
            }
            if (!isEmptyString(dto.getName())) {
                conditions.add(criteriaBuilder.like(qRoot.get("name"), dto.getName() + "%"));
            }
            if (!isEmptyString(dto.getDescription())) {
                conditions.add(criteriaBuilder.like(qRoot.get("description"), dto.getDescription() + "%"));
            }
        }
        System.out.println(conditions.toString());
        return conditions;
    }
}