package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Data Transfer Object (DTO) representing a Subject entity in the ORS application.
 * <p>
 * Maps to the {@code ST_SUBJECT} database table and holds subject-related
 * information such as name, description, and the associated course.
 * </p>
 *
 * @author Krati Trivedi
 */
@Entity
@Table(name = "ST_SUBJECT")
public class SubjectDTO extends BaseDTO {

    /**
     * The name of the subject.
     */
    @Column(name = "NAME", length = 50)
    private String name;

    /**
     * A brief description of the subject.
     */
    @Column(name = "DESCRIPTION", length = 50)
    private String description;

    /**
     * The ID of the course to which this subject belongs.
     */
    @Column(name = "COURSE_ID")
    private Long courseId;

    /**
     * The name of the course to which this subject belongs.
     */
    @Column(name = "COURSE_NAME", length = 50)
    private String courseName;

    /**
     * Returns the name of the subject.
     *
     * @return the subject name as a {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the subject.
     *
     * @param name the subject name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the subject.
     *
     * @return the description as a {@link String}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the subject.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the ID of the course to which this subject belongs.
     *
     * @return the course ID as a {@link Long}
     */
    public Long getCourseId() {
        return courseId;
    }

    /**
     * Sets the ID of the course to which this subject belongs.
     *
     * @param courseId the course ID to set
     */
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    /**
     * Returns the name of the course to which this subject belongs.
     *
     * @return the course name as a {@link String}
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the name of the course to which this subject belongs.
     *
     * @param courseName the course name to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Returns the display value for dropdown list usage.
     *
     * @return the subject name as a {@link String}
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
     * @return the subject name as the unique key value
     */
    @Override
    public String getUniqueValue() {
        // TODO Auto-generated method stub
        return name;
    }

    /**
     * Returns the human-readable label for this entity.
     *
     * @return {@code "Subject Name"} as the display label
     */
    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return "Subject Name";
    }

    /**
     * Returns the logical table name associated with this entity.
     *
     * @return {@code "Subject"} as the table name
     */
    @Override
    public String getTableName() {
        // TODO Auto-generated method stub
        return "Subject";
    }
}