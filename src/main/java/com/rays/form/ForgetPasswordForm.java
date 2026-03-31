package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseForm;

/**
 * Form class for handling forgot password requests.
 * This class captures the login ID of the user who has forgotten their password
 * and extends the BaseForm to inherit common form functionality.
 * 
 * @author Krati Trivedi
 */
public class ForgetPasswordForm extends BaseForm {

	@NotEmpty(message = "Login Id is required")
	private String login;

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
}