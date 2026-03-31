package com.rays.form;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseForm;
import com.rays.dto.TimetableDTO;

/**
 * Form class for handling timetable operations.
 * This class captures timetable details including course and subject information,
 * exam date and time, semester, and description. Extends BaseForm with TimetableDTO as the DTO type.
 * 
 * @author Krati Trivedi
 */
public class TimetableForm extends BaseForm<TimetableDTO> {
	
	@NotNull(message = "Course is required")
	private Long courseId;

	private String courseName;

	@NotNull(message = "Subject is required")
	private Long subjectId;

	private String subjectName;

	@NotNull(message = "Exam Date is required")
	private Date examDate;

	@NotEmpty(message = "Exam Time is required")
	private String examTime;

	@NotEmpty(message = "Semester is required")
	private String semester;

	@NotEmpty(message = "Description is required")
	private String description;

	/**
	 * Gets the course ID associated with the timetable.
	 * 
	 * @return the course ID
	 */
	public Long getCourseId() {
		return courseId;
	}

	/**
	 * Sets the course ID associated with the timetable.
	 * 
	 * @param courseId the course ID to set
	 */
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	/**
	 * Gets the name of the course associated with the timetable.
	 * 
	 * @return the course name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Sets the name of the course associated with the timetable.
	 * 
	 * @param courseName the course name to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Gets the subject ID associated with the timetable.
	 * 
	 * @return the subject ID
	 */
	public Long getSubjectId() {
		return subjectId;
	}

	/**
	 * Sets the subject ID associated with the timetable.
	 * 
	 * @param subjectId the subject ID to set
	 */
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * Gets the name of the subject associated with the timetable.
	 * 
	 * @return the subject name
	 */
	public String getSubjectName() {
		return subjectName;
	}

	/**
	 * Sets the name of the subject associated with the timetable.
	 * 
	 * @param subjectName the subject name to set
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/**
	 * Gets the exam date.
	 * 
	 * @return the exam date
	 */
	public Date getExamDate() {
		return examDate;
	}

	/**
	 * Sets the exam date.
	 * 
	 * @param examDate the exam date to set
	 */
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	/**
	 * Gets the exam time.
	 * 
	 * @return the exam time
	 */
	public String getExamTime() {
		return examTime;
	}

	/**
	 * Sets the exam time.
	 * 
	 * @param examTime the exam time to set
	 */
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	/**
	 * Gets the semester.
	 * 
	 * @return the semester
	 */
	public String getSemester() {
		return semester;
	}

	/**
	 * Sets the semester.
	 * 
	 * @param semester the semester to set
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}

	/**
	 * Gets the description of the timetable entry.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the timetable entry.
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Converts this form object to a TimetableDTO object.
	 * Initializes a new DTO, populates it with all timetable details,
	 * and returns the populated DTO.
	 * 
	 * @return the populated TimetableDTO object
	 */
	@Override
	public TimetableDTO getDto() {
		
		TimetableDTO dto = initDTO(new TimetableDTO());
		dto.setCourseId(courseId);
		dto.setCourseName(courseName);
		dto.setSubjectId(subjectId);
		dto.setSubjectName(subjectName);
		dto.setExamDate(examDate);
		dto.setExamTime(examTime);
		dto.setSemester(semester);
		dto.setDescription(description);

		return dto;
	}

}