package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Data Transfer Object (DTO) representing a Course entity in the ORS application.
 * <p>
 * Maps to the {@code ST_COURSE} database table and holds course-related
 * information such as name, duration, and description.
 * </p>
 *
 * @author Krati Trivedi
 */
@Entity
@Table(name = "ST_COURSE")
public class CourseDTO extends BaseDTO {

    /**
     * The name of the course.
     */
    @Column(name = "name", length = 50)
    private String name;

    /**
     * The duration of the course (e.g., "2 years", "6 months").
     */
    @Column(name = "DURATION", length = 50)
    private String duration;

    /**
     * A brief description of the course.
     */
    @Column(name = "DESCRIPTION", length = 50)
    private String description;

    /**
     * Returns the name of the course.
     *
     * @return the course name as a {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the course.
     *
     * @param name the course name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the duration of the course.
     *
     * @return the duration as a {@link String}
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the course.
     *
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * Returns the description of the course.
     *
     * @return the description as a {@link String}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the course.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the display value for dropdown list usage.
     *
     * @return the course name as a {@link String}
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
     * @return the course name as the unique key value
     */
    @Override
    public String getUniqueValue() {
        // TODO Auto-generated method stub
        return name;
    }

    /**
     * Returns the human-readable label for this entity.
     *
     * @return {@code "Course Name"} as the display label
     */
    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return "Course Name";
    }

    /**
     * Returns the logical table name associated with this entity.
     *
     * @return {@code "Course"} as the table name
     */
    @Override
    public String getTableName() {
        // TODO Auto-generated method stub
        return "Course";
    }
}