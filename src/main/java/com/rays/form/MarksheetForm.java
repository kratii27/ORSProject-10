package com.rays.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseForm;
import com.rays.dto.MarksheetDTO;

/**
 * Form class for handling marksheet operations.
 * This class captures student marksheet details including roll number, student ID,
 * student name, and marks obtained in Physics, Chemistry, and Maths subjects.
 * Extends BaseForm to inherit common form functionality with MarksheetDTO as the DTO type.
 * 
 * @author Krati Trivedi
 */
public class MarksheetForm extends BaseForm<MarksheetDTO> {
	
	@NotEmpty(message = "Roll No is required")
	protected String rollNo = null;

	@NotNull(message = "Student Name is required")
	@Min(1)
	protected Long studentId;

	protected String name = null;

	@NotNull(message = "Physics is required")
	@Max(99)
	@Min(0)
	protected Integer physics;

	@NotNull(message = "Chemistry is required")
	@Max(99)
	@Min(0)
	protected Integer chemistry;

	@NotNull(message = "Maths is required")
	@Max(99)
	@Min(0)
	protected Integer maths;

	/**
	 * Gets the roll number of the student.
	 * 
	 * @return the roll number
	 */
	public String getRollNo() {
		return rollNo;
	}

	/**
	 * Sets the roll number of the student.
	 * 
	 * @param rollNo the roll number to set
	 */
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	/**
	 * Gets the student ID.
	 * 
	 * @return the student ID
	 */
	public Long getStudentId() {
		return studentId;
	}

	/**
	 * Sets the student ID.
	 * 
	 * @param studentId the student ID to set (must be at least 1)
	 */
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	/**
	 * Gets the name of the student.
	 * 
	 * @return the student name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the student.
	 * 
	 * @param name the student name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the marks obtained in Physics.
	 * 
	 * @return the physics marks (0-99)
	 */
	public Integer getPhysics() {
		return physics;
	}

	/**
	 * Sets the marks obtained in Physics.
	 * 
	 * @param physics the physics marks to set (must be between 0 and 99)
	 */
	public void setPhysics(Integer physics) {
		this.physics = physics;
	}

	/**
	 * Gets the marks obtained in Chemistry.
	 * 
	 * @return the chemistry marks (0-99)
	 */
	public Integer getChemistry() {
		return chemistry;
	}

	/**
	 * Sets the marks obtained in Chemistry.
	 * 
	 * @param chemistry the chemistry marks to set (must be between 0 and 99)
	 */
	public void setChemistry(Integer chemistry) {
		this.chemistry = chemistry;
	}

	/**
	 * Gets the marks obtained in Maths.
	 * 
	 * @return the maths marks (0-99)
	 */
	public Integer getMaths() {
		return maths;
	}

	/**
	 * Sets the marks obtained in Maths.
	 * 
	 * @param maths the maths marks to set (must be between 0 and 99)
	 */
	public void setMaths(Integer maths) {
		this.maths = maths;
	}
	
	/**
	 * Converts this form object to a MarksheetDTO object.
	 * Initializes a new DTO, populates it with form data, and returns it.
	 * 
	 * @return the populated MarksheetDTO object
	 */
	@Override
	public MarksheetDTO getDto() {
		
		MarksheetDTO dto = initDTO(new MarksheetDTO());
		dto.setRollNo(rollNo);
		dto.setName(name);
		dto.setStudentId(studentId);
		dto.setPhysics(physics);
		dto.setChemistry(chemistry);
		dto.setMaths(maths);

		return dto;
	}

}