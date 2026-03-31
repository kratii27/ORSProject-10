package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Data Transfer Object (DTO) representing a Student entity in the ORS application.
 * <p>
 * Maps to the {@code ST_STUDENT} database table and holds student-related
 * information such as enrollment number, personal details, contact information,
 * and the associated college.
 * </p>
 *
 * @author Krati Trivedi
 */
@Entity
@Table(name = "ST_STUDENT")
public class StudentDTO extends BaseDTO {

    /**
     * The unique enrollment number of the student.
     */
    @Column(name = "ENROLLMENT_NO", length = 50)
    private String enrollmentNo;

    /**
     * The first name of the student.
     */
    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    /**
     * The last name of the student.
     */
    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    /**
     * The date of birth of the student.
     */
    @Column(name = "DOB")
    private Date dob;

    /**
     * The contact phone number of the student.
     */
    @Column(name = "PHONE_NO", length = 50)
    private String phoneNo;

    /**
     * The email address of the student.
     */
    @Column(name = "EMAIL", length = 50)
    private String email;

    /**
     * The ID of the college the student is enrolled in.
     */
    @Column(name = "COLLEGE_ID")
    private Long collegeId;

    /**
     * The name of the college the student is enrolled in.
     */
    @Column(name = "COLLEGE_NAME", length = 50)
    private String collegeName;

    /**
     * Returns the enrollment number of the student.
     *
     * @return the enrollment number as a {@link String}
     */
    public String getEnrollmentNo() {
        return enrollmentNo;
    }

    /**
     * Sets the enrollment number of the student.
     *
     * @param enrollmentNo the enrollment number to set
     */
    public void setEnrollmentNo(String enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
    }

    /**
     * Returns the first name of the student.
     *
     * @return the first name as a {@link String}
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the student.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the student.
     *
     * @return the last name as a {@link String}
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the student.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the date of birth of the student.
     *
     * @return the date of birth as a {@link Date}
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Sets the date of birth of the student.
     *
     * @param dob the date of birth to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * Returns the contact phone number of the student.
     *
     * @return the phone number as a {@link String}
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * Sets the contact phone number of the student.
     *
     * @param phoneNo the phone number to set
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * Returns the email address of the student.
     *
     * @return the email address as a {@link String}
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the student.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the ID of the college the student is enrolled in.
     *
     * @return the college ID as a {@link Long}
     */
    public Long getCollegeId() {
        return collegeId;
    }

    /**
     * Sets the ID of the college the student is enrolled in.
     *
     * @param collegeId the college ID to set
     */
    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    /**
     * Returns the name of the college the student is enrolled in.
     *
     * @return the college name as a {@link String}
     */
    public String getCollegeName() {
        return collegeName;
    }

    /**
     * Sets the name of the college the student is enrolled in.
     *
     * @param collegeName the college name to set
     */
    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
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
     * @return {@code "enrollmentNo"} as the unique key attribute
     */
    @Override
    public String getUniqueKey() {
        // TODO Auto-generated method stub
        return "enrollmentNo";
    }

    /**
     * Returns the value of the unique key attribute for this entity instance.
     *
     * @return the enrollment number as the unique key value
     */
    @Override
    public String getUniqueValue() {
        // TODO Auto-generated method stub
        return enrollmentNo;
    }

    /**
     * Returns the human-readable label for this entity.
     *
     * @return {@code "Enrollment No"} as the display label
     */
    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return "Enrollment No";
    }

    /**
     * Returns the logical table name associated with this entity.
     *
     * @return {@code "Student"} as the table name
     */
    @Override
    public String getTableName() {
        // TODO Auto-generated method stub
        return "Student";
    }
}