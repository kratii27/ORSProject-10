package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Form class for handling user profile operations.
 * This class captures user profile information including personal details,
 * contact information, and date of birth for profile management.
 * 
 * @author Krati Trivedi
 */
public class MyProfileForm {

	@NotEmpty(message = "First Name is required")
	private String firstName;

	@NotEmpty(message = "Last Name is required")
	private String lastName;

	@NotEmpty(message = "Login Id is required")
	private String login;

	@NotEmpty(message = "Gender is required")
	private String gender;

	@NotEmpty(message = "Mobile No is required")
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String phoneNo;

	@NotNull(message = "Date of birth is required")
	private Date dob;

	/**
	 * Gets the first name of the user.
	 * 
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the user.
	 * 
	 * @param firstName the first name to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name of the user.
	 * 
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the user.
	 * 
	 * @param lastName the last name to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the login ID of the user.
	 * 
	 * @return the login ID
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login ID of the user.
	 * 
	 * @param login the login ID to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Gets the gender of the user.
	 * 
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender of the user.
	 * 
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the phone number of the user.
	 * 
	 * @return the phone number (must be a 10-digit number)
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * Sets the phone number of the user.
	 * 
	 * @param phoneNo the phone number to set (must be a 10-digit number)
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * Gets the date of birth of the user.
	 * 
	 * @return the date of birth
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * Sets the date of birth of the user.
	 * 
	 * @param dob the date of birth to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}
}