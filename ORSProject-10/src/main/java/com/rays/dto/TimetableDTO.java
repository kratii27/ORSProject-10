package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "ST_TIMETABLE")
public class TimetableDTO extends BaseDTO {
	
	@Column(name = "COURSE_ID")
	private Long courseId;
	
	@Column(name = "COURSE_NAME", length = 50)
	private String courseName;
	
	@Column(name = "SUBJECT_ID")
	private Long subjectId;
	
	@Column(name = "SUBJECT_NAME", length = 50)
	private String subjectName;
	
	@Column(name = "EXAM_DATE")
	private Date examDate;
	
	@Column(name = "EXAM_TIME", length = 50)
	private String examTime;
	
	@Column(name = "SEMESTER", length = 50)
	private String semester;
	
	@Column(name = "DESCRIPTION", length = 50)
	private String description;
	

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

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getExamTime() {
		return examTime;
	}

	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUniqueKey() {
		// TODO Auto-generated method stub
		return "courseName";
	}

	@Override
	public String getUniqueValue() {
		// TODO Auto-generated method stub
		return courseName;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Course Name";
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "Timetable";
	}

}
