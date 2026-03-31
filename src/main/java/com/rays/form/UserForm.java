package com.rays.form;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.rays.common.BaseForm;
import com.rays.dto.UserDTO;

/**
 * Form class for handling user operations.
 * This class captures comprehensive user details including personal information,
 * login credentials, role assignment, contact information, and user status.
 * Extends BaseForm with UserDTO as the DTO type.
 * 
 * @author Krati Trivedi
 */
public class UserForm extends BaseForm<UserDTO> {

	@NotEmpty(message = "First Name is Required")
	private String firstName;
	
	@NotEmpty(message = "Last Name is Required")
	private String lastName;
	
	@NotEmpty(message = "Login Id is Required")
	private String login;
	
	@NotEmpty(message = "Password is Required")
	private String password;
	
	@NotNull(message = "Role Id is Required")
	@Min(1)
	private Long roleId;
	
	private String roleName = null;
	
	@NotNull(message = "Dob is Required")
	private Date dob;
	
	@NotEmpty(message = "Gender is Required")
	private String gender;
	
	@NotEmpty(message = "Phone No is Required")
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String phoneNo;
	
	@NotEmpty(message = "Alternate Phone No is Required")
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String alternatePhoneNo;
	
	@NotEmpty(message = "Status is Required")
	private String status;

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
	 * Gets the role ID assigned to the user.
	 * 
	 * @return the role ID
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * Sets the role ID assigned to the user.
	 * 
	 * @param roleId the role ID to set (must be at least 1)
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * Gets the role name assigned to the user.
	 * 
	 * @return the role name
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * Sets the role name assigned to the user.
	 * 
	 * @param roleName the role name to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	 * Gets the primary phone number of the user.
	 * 
	 * @return the phone number (must be a 10-digit number)
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * Sets the primary phone number of the user.
	 * 
	 * @param phoneNo the phone number to set (must be a 10-digit number)
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * Gets the alternate phone number of the user.
	 * 
	 * @return the alternate phone number (must be a 10-digit number)
	 */
	public String getAlternatePhoneNo() {
		return alternatePhoneNo;
	}

	/**
	 * Sets the alternate phone number of the user.
	 * 
	 * @param alternatePhoneNo the alternate phone number to set (must be a 10-digit number)
	 */
	public void setAlternatePhoneNo(String alternatePhoneNo) {
		this.alternatePhoneNo = alternatePhoneNo;
	}

	/**
	 * Gets the status of the user (e.g., Active, Inactive).
	 * 
	 * @return the user status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status of the user.
	 * 
	 * @param status the user status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Converts this form object to a UserDTO object.
	 * Initializes a new DTO, populates it with all user details,
	 * and returns the populated DTO.
	 * 
	 * @return the populated UserDTO object
	 */
	@Override
	public UserDTO getDto() {
		UserDTO dto = initDTO(new UserDTO());
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setLogin(login);
		dto.setPassword(password);
		dto.setRoleId(roleId);
		dto.setRoleName(roleName);
		dto.setDob(dob);
		dto.setGender(gender);
		dto.setPhoneNo(phoneNo);
		dto.setAlternatePhoneNo(alternatePhoneNo);
		dto.setStatus(status);
		return dto;
	}

}