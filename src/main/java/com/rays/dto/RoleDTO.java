package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Data Transfer Object (DTO) representing a Role entity in the ORS application.
 * <p>
 * Maps to the {@code ST_ROLE} database table and holds role-related
 * information such as name and description. Roles are used to define
 * access levels and permissions for users within the application.
 * </p>
 *
 * @author Krati Trivedi
 */
@Entity
@Table(name = "ST_ROLE")
public class RoleDTO extends BaseDTO {

    /**
     * The name of the role (e.g., "Admin", "Student").
     */
    @Column(name = "NAME", length = 50)
    private String name;

    /**
     * A brief description of the role and its associated permissions.
     */
    @Column(name = "DESCRIPTION", length = 50)
    private String description;

    /**
     * Returns the name of the role.
     *
     * @return the role name as a {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the role.
     *
     * @param name the role name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the role.
     *
     * @return the description as a {@link String}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the role.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the display value for dropdown list usage.
     *
     * @return the role name as a {@link String}
     */
    @Override
    public String getValue() {
        // TODO Auto-generated method stub
        return name;
    }

    /**
     * Returns the unique key attribute name used for duplicate checking.
     *
     * @return {@code "name"} as the unique key attribute
     */
    @Override
    public String getUniqueKey() {
        // TODO Auto-generated method stub
        return "name";
    }

    /**
     * Returns the value of the unique key attribute for this entity instance.
     *
     * @return the role name as the unique key value
     */
    @Override
    public String getUniqueValue() {
        // TODO Auto-generated method stub
        return name;
    }

    /**
     * Returns the human-readable label for this entity.
     *
     * @return {@code "Role Name"} as the display label
     */
    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return "Role Name";
    }

    /**
     * Returns the logical table name associated with this entity.
     *
     * @return {@code "ROLE"} as the table name
     */
    @Override
    public String getTableName() {
        // TODO Auto-generated method stub
        return "ROLE";
    }

    /**
     * Returns a string representation of this {@code RoleDTO} instance,
     * including the role name and description.
     *
     * @return a formatted string containing the name and description fields
     */
    @Override
    public String toString() {
        return "RoleDTO [name=" + name + ", description=" + description + "]";
    }
}