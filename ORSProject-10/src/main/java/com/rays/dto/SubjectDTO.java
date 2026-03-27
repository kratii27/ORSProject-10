package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;


@Entity
@Table(name = "ST_SUBJECT")
public class SubjectDTO extends BaseDTO {
	
	@Column(name = "NAME", length = 50)
	private String name;
	
	@Column(name = "DESCRIPTION", length = 50)
	private String description;
	
	@Column(name = "COURSE_ID")
	private Long courseId;
	
	@Column(name = "COURSE_NAME", length = 50)
	private String courseName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return name;
	}

	@Override
	public String getUniqueKey() {
		// TODO Auto-generated method stub
		return "name";
	}

	@Override
	public String getUniqueValue() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Subject Name";
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "Subject";
	}

}
