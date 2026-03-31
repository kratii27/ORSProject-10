package com.rays.common;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * Abstract base class for all DTO (Data Transfer Object) entities in the ORS application.
 * <p>
 * Provides common persistent fields such as primary key, audit fields (createdBy,
 * modifiedBy, createdDateTime, modifiedDateTime), and abstract methods that
 * subclasses must implement to define entity-specific metadata.
 * </p>
 * <p>
 * Annotated with {@code @MappedSuperclass} so that its fields are inherited
 * by all JPA entity subclasses without being mapped to a separate table.
 * </p>
 *
 * @author Krati Trivedi
 */
@MappedSuperclass
public abstract class BaseDTO implements DropdownList {

    /**
     * The primary key of the entity, auto-generated using the native strategy.
     */
    @Id
    @GeneratedValue(generator = "pk")
    @GenericGenerator(name = "pk", strategy = "native")
    @Column(name = "ID", nullable = false, unique = true)
    protected Long id;

    /**
     * The login identifier of the user who last modified this record.
     * Defaults to {@code "root"}.
     */
    @Column(name = "MODIFIED_BY", length = 50)
    protected String modifiedBy = "root";

    /**
     * The login identifier of the user who created this record.
     * Defaults to {@code "root"}.
     */
    @Column(name = "CREATED_BY", length = 50)
    protected String createdBy = "root";

    /**
     * The timestamp when this record was first created.
     */
    @Column(name = "CREATED_DATE_TIME")
    protected Timestamp createdDateTime;

    /**
     * The timestamp when this record was last modified.
     */
    @Column(name = "MODIFIED_DATE_TIME")
    protected Timestamp modifiedDateTime;

    /**
     * Returns the primary key of this entity.
     *
     * @return the {@link Long} ID of the entity
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the primary key of this entity.
     *
     * @param id the {@link Long} ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the login identifier of the user who last modified this record.
     *
     * @return the modifiedBy value as a {@link String}
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Sets the login identifier of the user who last modified this record.
     *
     * @param modifiedBy the login identifier to set
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Returns the login identifier of the user who created this record.
     *
     * @return the createdBy value as a {@link String}
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the login identifier of the user who created this record.
     *
     * @param createdBy the login identifier to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Returns the timestamp when this record was created.
     *
     * @return the {@link Timestamp} of record creation
     */
    public Timestamp getCreatedDateTime() {
        return createdDateTime;
    }

    /**
     * Sets the timestamp when this record was created.
     *
     * @param createdDateTime the creation {@link Timestamp} to set
     */
    public void setCreatedDateTime(Timestamp createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    /**
     * Returns the timestamp when this record was last modified.
     *
     * @return the {@link Timestamp} of the last modification
     */
    public Timestamp getModifiedDateTime() {
        return modifiedDateTime;
    }

    /**
     * Sets the timestamp when this record was last modified.
     *
     * @param modifiedDateTime the modification {@link Timestamp} to set
     */
    public void setModifiedDateTime(Timestamp modifiedDateTime) {
        this.modifiedDateTime = modifiedDateTime;
    }

    /**
     * Returns the string representation of the primary key,
     * used for populating dropdown lists.
     *
     * @return the ID as a {@link String}
     */
    @Override
    public String getKey() {
        // TODO Auto-generated method stub
        return String.valueOf(id);
    }

    /**
     * Returns the name of the entity attribute that serves as the unique key.
     * <p>
     * Must be implemented by each subclass to define its uniqueness constraint field.
     * </p>
     *
     * @return the unique key attribute name as a {@link String}
     */
    public abstract String getUniqueKey();

    /**
     * Returns the value of the unique key attribute for this entity instance.
     * <p>
     * Must be implemented by each subclass to return the actual unique field value.
     * </p>
     *
     * @return the unique key value as a {@link String}
     */
    public abstract String getUniqueValue();

    /**
     * Returns a human-readable label for this entity, typically used in UI displays.
     * <p>
     * Must be implemented by each subclass.
     * </p>
     *
     * @return the display label as a {@link String}
     */
    public abstract String getLabel();

    /**
     * Returns the database table name associated with this entity.
     * <p>
     * Must be implemented by each subclass.
     * </p>
     *
     * @return the table name as a {@link String}
     */
    public abstract String getTableName();
}