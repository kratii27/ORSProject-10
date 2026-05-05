package com.rays.form;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.rays.common.BaseForm;
import com.rays.dto.FeeStructureDTO;

public class FeeStructureForm extends BaseForm<FeeStructureDTO> {
	
	@NotNull(message = "Course Id is Required")
	@Min(1)
	private Long courseId;
	
	private String courseName;
	
	
	
	@NotNull(message = "Base Fee is Required")
	@Min(1)
	private Double baseFee;
	
	@NotNull(message = "Exam Fee is Required")
	@Min(1)
	private Double examFee;
	
	@NotNull(message = "Submission Date is Required")
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
	
	@Override
	public FeeStructureDTO getDto() {
		FeeStructureDTO dto = initDTO(new FeeStructureDTO());
		dto.setCourseId(courseId);
		dto.setCourseName(courseName);
		dto.setBaseFee(baseFee);
		dto.setExamFee(examFee);
		dto.setSubmissionDate(submissionDate);
		return dto;
	}
}