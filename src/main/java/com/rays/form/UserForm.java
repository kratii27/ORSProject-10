package com.rays.form;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.rays.common.BaseForm;
import com.rays.dto.UserDTO;

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAlternatePhoneNo() {
		return alternatePhoneNo;
	}

	public void setAlternatePhoneNo(String alternatePhoneNo) {
		this.alternatePhoneNo = alternatePhoneNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
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
