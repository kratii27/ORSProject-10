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
import com.rays.dto.RoleDTO;
import com.rays.dto.UserDTO;

/**
 * Data Access Object (DAO) implementation for {@link UserDTO} in the ORS application.
 * <p>
 * Extends {@link BaseDAOImpl} to inherit common JPA-based CRUD operations and
 * implements {@link UserDAOInt}. Provides entity-specific implementations for
 * retrieving the DTO class type, building dynamic search filter predicates based on
 * user attributes, and populating derived fields before persistence.
 * </p>
 *
 * @author Krati Trivedi
 */
@Repository
public class UserDAOImpl extends BaseDAOImpl<UserDTO> implements UserDAOInt {

    /**
     * DAO for retrieving {@link RoleDTO} data used to populate
     * the role name on the user record before persistence.
     */
    @Autowired
    RoleDAOint roleDao;

    /**
     * Returns the {@link Class} type of {@link UserDTO} managed by this DAO.
     *
     * @return {@code UserDTO.class}
     */
    @Override
    public Class<UserDTO> getDTOClass() {
        // TODO Auto-generated method stub
        return UserDTO.class;
    }

    /**
     * Builds and returns a list of JPA {@link Predicate} conditions for filtering
     * user records based on the non-empty or non-null fields of the given {@link UserDTO}.
     * <p>
     * The following fields are used for filtering when non-empty or non-null:
     * <ul>
     *   <li>{@code firstName} — filters users whose first name starts with the given value</li>
     *   <li>{@code lastName} — filters users whose last name starts with the given value</li>
     *   <li>{@code login} — filters users whose login ID starts with the given value</li>
     *   <li>{@code roleId} — filters users by exact role ID match when non-zero</li>
     *   <li>{@code dob} — filters users by exact date of birth match</li>
     *   <li>{@code status} — filters users by exact status match</li>
     * </ul>
     * </p>
     *
     * @param dto             the {@link UserDTO} containing filter values
     * @param criteriaBuilder the {@link CriteriaBuilder} used to construct predicates
     * @param qRoot           the {@link Root} of the criteria query for {@link UserDTO}
     * @return a list of {@link Predicate} conditions for the WHERE clause
     */
    @Override
    public List<Predicate> getWhereClause(UserDTO dto, CriteriaBuilder criteriaBuilder, Root<UserDTO> qRoot) {

        List<Predicate> conditions = new ArrayList<Predicate>();

        if (!isEmptyString(dto.getFirstName())) {
            conditions.add(criteriaBuilder.like(qRoot.get("firstName"), dto.getFirstName() + "%"));
        }
        if (!isEmptyString(dto.getLastName())) {
            conditions.add(criteriaBuilder.like(qRoot.get("lastName"), dto.getLastName() + "%"));
        }
        if (!isEmptyString(dto.getLogin())) {
            conditions.add(criteriaBuilder.like(qRoot.get("login"), dto.getLogin() + "%"));
        }
        if (!isZeroNumber(dto.getRoleId())) {
            conditions.add(criteriaBuilder.equal(qRoot.get("roleId"), dto.getRoleId()));
        }
        if (isNotNull(dto.getDob())) {
            conditions.add(criteriaBuilder.equal(qRoot.get("dob"), dto.getDob()));
        }
        if (!isEmptyString(dto.getStatus())) {
            conditions.add(criteriaBuilder.like(qRoot.get("status"), dto.getStatus()));
        }
        return conditions;
    }

    /**
     * Populates derived fields on the {@link UserDTO} before it is persisted.
     * <p>
     * Performs the following population steps:
     * <ul>
     *   <li>If a valid {@code roleId} is present, retrieves the corresponding
     *       {@link RoleDTO} and sets the role name on the user record.</li>
     *   <li>If a valid {@code id} is present, retrieves the existing user record
     *       and preserves the {@code lastLogin} timestamp.</li>
     *   <li>If a valid {@code id} is present, retrieves the existing user record
     *       and preserves the {@code imageId} to prevent it from being overwritten.</li>
     * </ul>
     * </p>
     *
     * @param dto         the {@link UserDTO} to be populated
     * @param userContext the current user's context
     */
    @Override
    protected void populate(UserDTO dto, UserContext userContext) {
        if (dto.getRoleId() != null && dto.getRoleId() > 0) {
            RoleDTO roleDTO = roleDao.findByPk(dto.getRoleId(), userContext);
            if (roleDTO != null) {
                dto.setRoleName(roleDTO.getName());
            }
        }
        if (dto.getId() != null && dto.getId() > 0) {
            UserDTO user = findByPk(dto.getId(), userContext);
            if (user != null) {
                dto.setLastLogin(user.getLastLogin());
            }
        }
        if (dto.getId() != null && dto.getId() > 0) {
            UserDTO userData = findByPk(dto.getId(), null);
            if (userData != null) {
                dto.setImageId(userData.getImageId());
            }
        }
    }
}