package com.rays.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseForm;
import com.rays.dto.SubjectDTO;

/**
 * Form class for handling subject operations.
 * This class captures subject details including associated course information,
 * subject name, and description. Extends BaseForm with SubjectDTO as the DTO type.
 * 
 * @author Krati Trivedi
 */
public class SubjectForm extends BaseForm<SubjectDTO> {
	
	@NotNull(message = "Course is required")
	@Min(1)
	private Long courseId = 0L;

	private String courseName;

	@NotEmpty(message = "Name is required")
	private String name;

	@NotEmpty(message = "Description is required")
	private String description;

	/**
	 * Gets the course ID associated with the subject.
	 * 
	 * @return the course ID
	 */
	public Long getCourseId() {
		return courseId;
	}

	/**
	 * Sets the course ID associated with the subject.
	 * 
	 * @param courseId the course ID to set (must be at least 1)
	 */
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	/**
	 * Gets the name of the course associated with the subject.
	 * 
	 * @return the course name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Sets the name of the course associated with the subject.
	 * 
	 * @param courseName the course name to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Gets the name of the subject.
	 * 
	 * @return the subject name
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
	 * Gets the description of the subject.
	 * 
	 * @return the subject description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the subject.
	 * 
	 * @param description the subject description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	/**
	 * Converts this form object to a SubjectDTO object.
	 * Initializes a new DTO, populates it with course ID, course name,
	 * subject name, and description, and returns the populated DTO.
	 * 
	 * @return the populated SubjectDTO object
	 */
	@Override
	public SubjectDTO getDto() {
		
		SubjectDTO dto = initDTO(new SubjectDTO());

		dto.setCourseId(courseId);
		dto.setCourseName(courseName);
		dto.setName(name);
		dto.setDescription(description);
		
		return dto;
	}
	

}