package com.rays.form;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Form class for handling user registration requests.
 * This class captures user registration details including personal information,
 * login credentials, date of birth, gender, and contact number.
 * 
 * @author Krati Trivedi
 */
public class UserRegistrationForm {

	@NotEmpty(message = "First Name is required")
	private String firstName;

	@NotEmpty(message = "Last Name is required")
	private String lastName;

	@Email
	@NotEmpty(message = "Login Id is required")
	private String login;

	@NotEmpty(message = "Password is required")
	private String password;

	@NotNull(message = "Date of birth is required")
	private Date dob;

	@NotEmpty(message = "Gender is required")
	private String gender;

	@NotEmpty(message = "Mobile No is required")
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String phone;

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
	 * The login ID must be a valid email address format.
	 * 
	 * @return the login ID
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login ID of the user.
	 * 
	 * @param login the login ID to set (must be a valid email format)
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Gets the password of the user.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the user.
	 * 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * Gets the mobile phone number of the user.
	 * 
	 * @return the mobile number (must be a 10-digit number)
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the mobile phone number of the user.
	 * 
	 * @param phone the mobile number to set (must be a 10-digit number)
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
}