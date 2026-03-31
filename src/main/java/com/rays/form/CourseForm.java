package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseForm;
import com.rays.dto.CourseDTO;

/**
 * Form class for handling Course add and update requests in the ORS application.
 * <p>
 * Extends {@link BaseForm} parameterized with {@link CourseDTO} and carries
 * course-related input fields submitted from the client. Validation constraints
 * are applied to ensure all required fields are provided.
 * </p>
 *
 * @author Krati Trivedi
 */
public class CourseForm extends BaseForm<CourseDTO> {

    /**
     * The name of the course. Must not be empty.
     */
    @NotEmpty(message = "Name is required")
    private String name;

    /**
     * The duration of the course. Must not be empty.
     */
    @NotEmpty(message = "Duration is required")
    private String duration;

    /**
     * A brief description of the course. Must not be empty.
     */
    @NotEmpty(message = "Description is required")
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
     * Constructs and returns a {@link CourseDTO} populated with the form's field values.
     * <p>
     * Initializes the DTO using {@link #initDTO(com.rays.common.BaseDTO)} to set
     * the ID appropriately for add or update operations, then maps all form
     * fields onto the DTO.
     * </p>
     *
     * @return a fully populated {@link CourseDTO} instance
     */
    @Override
    public CourseDTO getDto() {
        CourseDTO dto = initDTO(new CourseDTO());
        dto.setName(name);
        dto.setDuration(duration);
        dto.setDescription(description);
        return dto;
    }
}