package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Data Transfer Object (DTO) representing a Faculty entity in the ORS application.
 * <p>
 * Maps to the {@code ST_FACULTY} database table and holds faculty-related
 * information such as personal details, contact information, qualification,
 * and associations with college, subject, and course entities.
 * </p>
 *
 * @author Krati Trivedi
 */
@Entity
@Table(name = "ST_FACULTY")
public class FacultyDTO extends BaseDTO {

    /**
     * The first name of the faculty member.
     */
    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    /**
     * The last name of the faculty member.
     */
    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    /**
     * The date of birth of the faculty member.
     */
    @Column(name = "DOB")
    private Date dob;

    /**
     * The gender of the faculty member.
     */
    @Column(name = "GENDER", length = 50)
    private String gender;

    /**
     * The contact phone number of the faculty member.
     */
    @Column(name = "PHONE_NO", length = 50)
    private String phoneNo;

    /**
     * The email address of the faculty member.
     */
    @Column(name = "EMAIL", length = 50)
    private String email;

    /**
     * The highest qualification of the faculty member.
     */
    @Column(name = "QUALIFICATION", length = 50)
    private String qualification;

    /**
     * The ID of the college associated with the faculty member.
     */
    @Column(name = "COLLEGE_ID")
    private Long collegeId;

    /**
     * The name of the college associated with the faculty member.
     */
    @Column(name = "COLLEGE_NAME", length = 50)
    private String collegeName;

    /**
     * The ID of the subject taught by the faculty member.
     */
    @Column(name = "SUBJECT_ID")
    private Long subjectId;

    /**
     * The name of the subject taught by the faculty member.
     */
    @Column(name = "SUBJECT_NAME", length = 50)
    private String subjectName;

    /**
     * The ID of the course associated with the faculty member.
     */
    @Column(name = "COURSE_ID")
    private Long courseId;

    /**
     * The name of the course associated with the faculty member.
     */
    @Column(name = "COURSE_NAME", length = 50)
    private String courseName;

    /**
     * Returns the first name of the faculty member.
     *
     * @return the first name as a {@link String}
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the faculty member.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the faculty member.
     *
     * @return the last name as a {@link String}
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the faculty member.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the date of birth of the faculty member.
     *
     * @return the date of birth as a {@link Date}
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Sets the date of birth of the faculty member.
     *
     * @param dob the date of birth to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * Returns the gender of the faculty member.
     *
     * @return the gender as a {@link String}
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the faculty member.
     *
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Returns the contact phone number of the faculty member.
     *
     * @return the phone number as a {@link String}
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * Sets the contact phone number of the faculty member.
     *
     * @param phoneNo the phone number to set
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * Returns the email address of the faculty member.
     *
     * @return the email address as a {@link String}
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the faculty member.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the highest qualification of the faculty member.
     *
     * @return the qualification as a {@link String}
     */
    public String getQualification() {
        return qualification;
    }

    /**
     * Sets the highest qualification of the faculty member.
     *
     * @param qualification the qualification to set
     */
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    /**
     * Returns the ID of the college associated with the faculty member.
     *
     * @return the college ID as a {@link Long}
     */
    public Long getCollegeId() {
        return collegeId;
    }

    /**
     * Sets the ID of the college associated with the faculty member.
     *
     * @param collegeId the college ID to set
     */
    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    /**
     * Returns the name of the college associated with the faculty member.
     *
     * @return the college name as a {@link String}
     */
    public String getCollegeName() {
        return collegeName;
    }

    /**
     * Sets the name of the college associated with the faculty member.
     *
     * @param collegeName the college name to set
     */
    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    /**
     * Returns the ID of the subject taught by the faculty member.
     *
     * @return the subject ID as a {@link Long}
     */
    public Long getSubjectId() {
        return subjectId;
    }

    /**
     * Sets the ID of the subject taught by the faculty member.
     *
     * @param subjectId the subject ID to set
     */
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * Returns the name of the subject taught by the faculty member.
     *
     * @return the subject name as a {@link String}
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * Sets the name of the subject taught by the faculty member.
     *
     * @param subjectName the subject name to set
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * Returns the ID of the course associated with the faculty member.
     *
     * @return the course ID as a {@link Long}
     */
    public Long getCourseId() {
        return courseId;
    }

    /**
     * Sets the ID of the course associated with the faculty member.
     *
     * @param courseId the course ID to set
     */
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    /**
     * Returns the name of the course associated with the faculty member.
     *
     * @return the course name as a {@link String}
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the name of the course associated with the faculty member.
     *
     * @param courseName the course name to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Returns the display value for dropdown list usage.
     *
     * @return the full name (first name + last name) as a {@link String}
     */
    @Override
    public String getValue() {
        // TODO Auto-generated method stub
        return firstName + " " + lastName;
    }

    /**
     * Returns the unique key attribute name used for duplicate checking.
     *
     * @return {@code "email"} as the unique key attribute
     */
    @Override
    public String getUniqueKey() {
        // TODO Auto-generated method stub
        return "email";
    }

    /**
     * Returns the value of the unique key attribute for this entity instance.
     *
     * @return the email address as the unique key value
     */
    @Override
    public String getUniqueValue() {
        // TODO Auto-generated method stub
        return email;
    }

    /**
     * Returns the human-readable label for this entity.
     *
     * @return {@code "Email Id"} as the display label
     */
    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return "Email Id";
    }

    /**
     * Returns the logical table name associated with this entity.
     *
     * @return {@code "Faculty"} as the table name
     */
    @Override
    public String getTableName() {
        // TODO Auto-generated method stub
        return "Faculty";
    }
}