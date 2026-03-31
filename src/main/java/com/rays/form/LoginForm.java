package com.rays.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Form class for handling user login requests.
 * This class captures the login credentials including login ID/email and password
 * for user authentication.
 * 
 * @author Krati Trivedi
 */
public class LoginForm {

	@NotEmpty(message = "Login Id is required")
	@Email
	private String login;

	@NotEmpty(message = "Password is required")
	private String password;

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
}