package com.rays.dto;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.rays.common.BaseDTO;

@Entity
@Table(name = "ST_FEE_STRUCTURE")
public class FeeStructureDTO extends BaseDTO {
	
	@Column(name = "COURSE_ID")
	private Long courseId;
	
	@Column(name = "COURSE_NAME", length = 50)
	private String courseName;
	
	@Column(name = "BASE_FEE")
	private Double baseFee;
	
	@Column(name = "EXAM_FEE")
	private Double examFee;
	
	@Column(name = "SUBMISSION_DATE")
	private Date submissionDate;
	
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
	
	
	public Double getBaseFee() {
		return baseFee;
	}
	
	public void setBaseFee(Double baseFee) {
		this.baseFee = baseFee;
	}
	
	public Double getExamFee() {
		return examFee;
	}
	
	public void setExamFee(Double examFee) {
		this.examFee = examFee;
	}
	
	public Date getSubmissionDate() {
		return submissionDate;
	}
	
	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}
	
	public String getName() {
		return courseName;
	}
	
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return getName();
	}
	
	@Override
	public String getUniqueKey() {
		// TODO Auto-generated method stub
		return "courseId";
	}
	
	@Override
	public String getUniqueValue() {
		// TODO Auto-generated method stub
		return String.valueOf(courseId);
	}
	
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Course";
	}
	
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "FeeStructure";
	}
}