package com.rays.form;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.rays.common.BaseForm;
import com.rays.dto.FacultyDTO;

/**
 * Form class for handling Faculty add and update requests in the ORS application.
 * <p>
 * Extends {@link BaseForm} parameterized with {@link FacultyDTO} and carries
 * faculty-related input fields submitted from the client. Validation constraints
 * are applied to ensure all required fields are provided in the correct format.
 * </p>
 *
 * @author Krati Trivedi
 */
public class FacultyForm extends BaseForm<FacultyDTO> {

    /**
     * The first name of the faculty member. Must not be empty.
     */
    @NotEmpty(message = "First Name is required")
    private String firstName;

    /**
     * The last name of the faculty member. Must not be empty.
     */
    @NotEmpty(message = "Last Name is required")
    private String lastName;

    /**
     * The date of birth of the faculty member. Must not be null.
     */
    @NotNull(message = "Date of birth is required")
    private Date dob;

    /**
     * The gender of the faculty member. Must not be empty.
     */
    @NotEmpty(message = "Gender is required")
    private String gender;

    /**
     * The contact phone number of the faculty member. Must not be null
     * and must be exactly 10 digits or empty.
     */
    @NotNull(message = "Phone No is required")
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String phoneNo;

    /**
     * The email address of the faculty member. Must not be empty.
     */
    @NotEmpty(message = "Email ID is required")
    private String email;

    /**
     * The highest qualification of the faculty member. Must not be empty.
     */
    @NotEmpty(message = "Qualification is required")
    private String qualification;

    /**
     * The ID of the college associated with the faculty member.
     * Must not be null and must be at least 1.
     */
    @NotNull(message = "College is required")
    @Min(1)
    private Long collegeId;

    /**
     * The name of the college associated with the faculty member.
     */
    private String collegeName;

    /**
     * The ID of the course associated with the faculty member.
     * Must not be null and must be at least 1.
     */
    @NotNull(message = "Course is required")
    @Min(1)
    private Long courseId;

    /**
     * The name of the course associated with the faculty member.
     */
    private String courseName;

    /**
     * The ID of the subject taught by the faculty member.
     * Must not be null and must be at least 1.
     */
    @NotNull(message = "Subject is required")
    @Min(1)
    private Long subjectId;

    /**
     * The name of the subject taught by the faculty member.
     */
    private String subjectName;

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
     * Constructs and returns a {@link FacultyDTO} populated with the form's field values.
     * <p>
     * Initializes the DTO using {@link #initDTO(com.rays.common.BaseDTO)} to set
     * the ID appropriately for add or update operations, then maps all form
     * fields onto the DTO.
     * </p>
     *
     * @return a fully populated {@link FacultyDTO} instance
     */
    @Override
    public FacultyDTO getDto() {
        FacultyDTO dto = initDTO(new FacultyDTO());
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setDob(dob);
        dto.setGender(gender);
        dto.setPhoneNo(phoneNo);
        dto.setEmail(email);
        dto.setQualification(qualification);
        dto.setCollegeId(collegeId);
        dto.setCollegeName(collegeName);
        dto.setCourseId(courseId);
        dto.setCourseName(courseName);
        dto.setSubjectId(subjectId);
        dto.setSubjectName(subjectName);
        return dto;
    }
}