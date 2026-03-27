package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "ST_FACULTY")
public class FacultyDTO extends BaseDTO {
	
	@Column(name = "FIRST_NAME", length = 50)
	private String firstName;
	
	@Column(name = "LAST_NAME", length = 50)
	private String lastName;
	
	@Column(name = "DOB")
	private Date dob;
	
	@Column(name = "GENDER", length = 50)
	private String gender;
	
	@Column(name = "PHONE_NO", length = 50)
	private String phoneNo;
	
	@Column(name = "EMAIL", length = 50)
	private String email;
	
	@Column(name = "QUALIFICATION", length = 50)
	private String qualification;
	
	@Column(name = "COLLEGE_ID")
	private Long collegeId;
	
	@Column(name = "COLLEGE_NAME", length = 50)
	private String collegeName;
	
	@Column(name = "SUBJECT_ID")
	private Long subjectId;
	
	@Column(name = "SUBJECT_NAME", length = 50)
	private String subjectName;
	
	@Column(name = "COURSE_ID")
	private Long courseId;
	
	@Column(name = "COURSE_NAME", length = 50)
	private String courseName;
	
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return firstName + " " + lastName;
	}

	@Override
	public String getUniqueKey() {
		// TODO Auto-generated method stub
		return "email";
	}

	@Override
	public String getUniqueValue() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Email Id";
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "Faculty";
	}

}
