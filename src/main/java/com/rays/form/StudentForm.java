package com.rays.form;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.rays.common.BaseForm;
import com.rays.dto.StudentDTO;

/**
 * Form class for handling student operations.
 * This class captures student details including enrollment number, personal information,
 * contact details, email, and associated college information. Extends BaseForm with
 * StudentDTO as the DTO type.
 * 
 * @author Krati Trivedi
 */
public class StudentForm extends BaseForm<StudentDTO> {
	
	@NotEmpty(message = "enrollment No is required")
	private String enrollmentNo;

	@NotEmpty(message = "First Name is required")
	private String firstName;

	@NotEmpty(message = "Last Name is required")
	private String lastName;

	@NotNull(message = "Date of birth is required")
	private Date dob;

	@NotNull(message = "Phone No is required")
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String phoneNo;

	@NotEmpty(message = "Email ID is required")
	@Email
	private String email;

	@NotNull(message = "College Name is required")
	@Min(1)
	private Long collegeId;

	private String collegeName;

	/**
	 * Gets the enrollment number of the student.
	 * 
	 * @return the enrollment number
	 */
	public String getEnrollmentNo() {
		return enrollmentNo;
	}

	/**
	 * Sets the enrollment number of the student.
	 * 
	 * @param enrolNo the enrollment number to set
	 */
	public void setEnrollmentNo(String enrolNo) {
		this.enrollmentNo = enrolNo;
	}

	/**
	 * Gets the first name of the student.
	 * 
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the student.
	 * 
	 * @param firstName the first name to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name of the student.
	 * 
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the student.
	 * 
	 * @param lastName the last name to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the date of birth of the student.
	 * 
	 * @return the date of birth
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * Sets the date of birth of the student.
	 * 
	 * @param dob the date of birth to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * Gets the phone number of the student.
	 * 
	 * @return the phone number (must be a 10-digit number)
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * Sets the phone number of the student.
	 * 
	 * @param phoneNo the phone number to set (must be a 10-digit number)
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * Gets the email address of the student.
	 * 
	 * @return the email address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the student.
	 * 
	 * @param email the email address to set (must be a valid email format)
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the college ID associated with the student.
	 * 
	 * @return the college ID
	 */
	public Long getCollegeId() {
		return collegeId;
	}

	/**
	 * Sets the college ID associated with the student.
	 * 
	 * @param collegeId the college ID to set (must be at least 1)
	 */
	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

	/**
	 * Gets the name of the college associated with the student.
	 * 
	 * @return the college name
	 */
	public String getCollegeName() {
		return collegeName;
	}

	/**
	 * Sets the name of the college associated with the student.
	 * 
	 * @param collegeName the college name to set
	 */
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	
	/**
	 * Converts this form object to a StudentDTO object.
	 * Initializes a new DTO, populates it with all student details,
	 * and returns the populated DTO.
	 * 
	 * @return the populated StudentDTO object
	 */
	@Override
	public StudentDTO getDto() {
		StudentDTO dto = initDTO(new StudentDTO());

		dto.setEnrollmentNo(enrollmentNo);
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setDob(dob);
		dto.setPhoneNo(phoneNo);
		dto.setEmail(email);
		dto.setCollegeId(collegeId);
		dto.setCollegeName(collegeName);

		return dto;
	}

}