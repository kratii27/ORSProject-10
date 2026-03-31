package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Data Transfer Object (DTO) representing a Timetable entity in the ORS application.
 * <p>
 * Maps to the {@code ST_TIMETABLE} database table and holds timetable-related
 * information such as the associated course, subject, exam date and time,
 * semester, and description.
 * </p>
 *
 * @author Krati Trivedi
 */
@Entity
@Table(name = "ST_TIMETABLE")
public class TimetableDTO extends BaseDTO {

    /**
     * The ID of the course associated with this timetable entry.
     */
    @Column(name = "COURSE_ID")
    private Long courseId;

    /**
     * The name of the course associated with this timetable entry.
     */
    @Column(name = "COURSE_NAME", length = 50)
    private String courseName;

    /**
     * The ID of the subject scheduled in this timetable entry.
     */
    @Column(name = "SUBJECT_ID")
    private Long subjectId;

    /**
     * The name of the subject scheduled in this timetable entry.
     */
    @Column(name = "SUBJECT_NAME", length = 50)
    private String subjectName;

    /**
     * The date on which the exam is scheduled.
     */
    @Column(name = "EXAM_DATE")
    private Date examDate;

    /**
     * The time at which the exam is scheduled.
     */
    @Column(name = "EXAM_TIME", length = 50)
    private String examTime;

    /**
     * The semester to which this timetable entry belongs.
     */
    @Column(name = "SEMESTER", length = 50)
    private String semester;

    /**
     * A brief description of the timetable entry.
     */
    @Column(name = "DESCRIPTION", length = 50)
    private String description;

    /**
     * Returns the ID of the course associated with this timetable entry.
     *
     * @return the course ID as a {@link Long}
     */
    public Long getCourseId() {
        return courseId;
    }

    /**
     * Sets the ID of the course associated with this timetable entry.
     *
     * @param courseId the course ID to set
     */
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    /**
     * Returns the name of the course associated with this timetable entry.
     *
     * @return the course name as a {@link String}
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the name of the course associated with this timetable entry.
     *
     * @param courseName the course name to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Returns the ID of the subject scheduled in this timetable entry.
     *
     * @return the subject ID as a {@link Long}
     */
    public Long getSubjectId() {
        return subjectId;
    }

    /**
     * Sets the ID of the subject scheduled in this timetable entry.
     *
     * @param subjectId the subject ID to set
     */
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * Returns the name of the subject scheduled in this timetable entry.
     *
     * @return the subject name as a {@link String}
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Sets the name of the subject scheduled in this timetable entry.
     *
     * @param subjectName the subject name to set
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * Returns the date on which the exam is scheduled.
     *
     * @return the exam date as a {@link Date}
     */
    public Date getExamDate() {
        return examDate;
    }

    /**
     * Sets the date on which the exam is scheduled.
     *
     * @param examDate the exam date to set
     */
    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    /**
     * Returns the time at which the exam is scheduled.
     *
     * @return the exam time as a {@link String}
     */
    public String getExamTime() {
        return examTime;
    }

    /**
     * Sets the time at which the exam is scheduled.
     *
     * @param examTime the exam time to set
     */
    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    /**
     * Returns the semester to which this timetable entry belongs.
     *
     * @return the semester as a {@link String}
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Sets the semester to which this timetable entry belongs.
     *
     * @param semester the semester to set
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * Returns the description of this timetable entry.
     *
     * @return the description as a {@link String}
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of this timetable entry.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the display value for dropdown list usage.
     * <p>
     * Not applicable for timetable entries; always returns {@code null}.
     * </p>
     *
     * @return {@code null}
     */
    @Override
    public String getValue() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Returns the unique key attribute name used for duplicate checking.
     *
     * @return {@code "courseName"} as the unique key attribute
     */
    @Override
    public String getUniqueKey() {
        // TODO Auto-generated method stub
        return "courseName";
    }

    /**
     * Returns the value of the unique key attribute for this entity instance.
     *
     * @return the course name as the unique key value
     */
    @Override
    public String getUniqueValue() {
        // TODO Auto-generated method stub
        return courseName;
    }

    /**
     * Returns the human-readable label for this entity.
     *
     * @return {@code "Course Name"} as the display label
     */
    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return "Course Name";
    }

    /**
     * Returns the logical table name associated with this entity.
     *
     * @return {@code "Timetable"} as the table name
     */
    @Override
    public String getTableName() {
        // TODO Auto-generated method stub
        return "Timetable";
    }
}