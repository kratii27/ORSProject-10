package com.rays.dto;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "ST_USER")
public class UserDTO extends BaseDTO{

	@Column(name = "FIRST_NAME", length = 50)
	private String firstName;
	
	@Column(name = "LAST_NAME", length = 50)
	private String lastName;
	
	@Column(name = "LOGIN", length = 50)
	private String login;
	
	@Column(name = "PASSWORD", length = 50)
	private String password;
	
	@Column(name = "ROLE_ID")
	private Long roleId;
	
	@Column(name = "ROLE_NAME", length = 50)
	private String roleName = null;
	
	@Column(name = "DOB")
	private Date dob;
	
	@Column(name = "GENDER", length = 50)
	private String gender;
	
	@Column(name = "PHONE_NO", length = 50)
	private String phoneNo;
	
	@Column(name = "ALTERNATE_PHONE_NO", length = 50)
	private String alternatePhoneNo;
	
	@Column(name = "STATUS", length = 50)
	private String status;
	
	@Column(name = "last_login")
	private Timestamp lastLogin;

	@Column(name = "unsucess_login")
	private Integer unsucessfullLoginAttempt = 0;

	@Column(name = "image_id")
	private Long imageId;
	

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

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Integer getUnsucessfullLoginAttempt() {
		return unsucessfullLoginAttempt;
	}

	public void setUnsucessfullLoginAttempt(Integer unsucessfullLoginAttempt) {
		this.unsucessfullLoginAttempt = unsucessfullLoginAttempt;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	
	public String getName() {
		return firstName + " " + lastName;
		
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return getName(); 
	}

	@Override
	public String getUniqueKey() {
		// TODO Auto-generated method stub
		return "login";
	}

	@Override
	public String getUniqueValue() {
		// TODO Auto-generated method stub
		return login;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Login Id";
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "User";
	}
	
	
	
	
}
