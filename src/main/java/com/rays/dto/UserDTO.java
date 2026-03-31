package com.rays.dto;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Data Transfer Object (DTO) representing a User entity in the ORS application.
 * <p>
 * Maps to the {@code ST_USER} database table and holds user-related
 * information such as personal details, login credentials, role assignment,
 * account status, and profile picture reference.
 * </p>
 *
 * @author Krati Trivedi
 */
@Entity
@Table(name = "ST_USER")
public class UserDTO extends BaseDTO {

    /**
     * The first name of the user.
     */
    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    /**
     * The last name of the user.
     */
    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    /**
     * The login ID (typically email) of the user.
     */
    @Column(name = "LOGIN", length = 50)
    private String login;

    /**
     * The password of the user.
     */
    @Column(name = "PASSWORD", length = 50)
    private String password;

    /**
     * The ID of the role assigned to the user.
     */
    @Column(name = "ROLE_ID")
    private Long roleId;

    /**
     * The name of the role assigned to the user. Defaults to {@code null}.
     */
    @Column(name = "ROLE_NAME", length = 50)
    private String roleName = null;

    /**
     * The date of birth of the user.
     */
    @Column(name = "DOB")
    private Date dob;

    /**
     * The gender of the user.
     */
    @Column(name = "GENDER", length = 50)
    private String gender;

    /**
     * The primary contact phone number of the user.
     */
    @Column(name = "PHONE_NO", length = 50)
    private String phoneNo;

    /**
     * The alternate contact phone number of the user.
     */
    @Column(name = "ALTERNATE_PHONE_NO", length = 50)
    private String alternatePhoneNo;

    /**
     * The account status of the user (e.g., "Active", "Inactive").
     */
    @Column(name = "STATUS", length = 50)
    private String status;

    /**
     * The timestamp of the user's last successful login.
     */
    @Column(name = "last_login")
    private Timestamp lastLogin;

    /**
     * The count of consecutive unsuccessful login attempts. Defaults to {@code 0}.
     */
    @Column(name = "unsucess_login")
    private Integer unsucessfullLoginAttempt = 0;

    /**
     * The ID of the profile picture attachment associated with this user.
     */
    @Column(name = "image_id")
    private Long imageId;

    /**
     * Returns the first name of the user.
     *
     * @return the first name as a {@link String}
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the user.
     *
     * @return the last name as a {@link String}
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the login ID of the user.
     *
     * @return the login ID as a {@link String}
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login ID of the user.
     *
     * @param login the login ID to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password as a {@link String}
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the ID of the role assigned to the user.
     *
     * @return the role ID as a {@link Long}
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * Sets the ID of the role assigned to the user.
     *
     * @param roleId the role ID to set
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * Returns the name of the role assigned to the user.
     *
     * @return the role name as a {@link String}
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the name of the role assigned to the user.
     *
     * @param roleName the role name to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Returns the date of birth of the user.
     *
     * @return the date of birth as a {@link Date}
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Sets the date of birth of the user.
     *
     * @param dob the date of birth to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * Returns the gender of the user.
     *
     * @return the gender as a {@link String}
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the user.
     *
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Returns the primary contact phone number of the user.
     *
     * @return the phone number as a {@link String}
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * Sets the primary contact phone number of the user.
     *
     * @param phoneNo the phone number to set
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * Returns the alternate contact phone number of the user.
     *
     * @return the alternate phone number as a {@link String}
     */
    public String getAlternatePhoneNo() {
        return alternatePhoneNo;
    }

    /**
     * Sets the alternate contact phone number of the user.
     *
     * @param alternatePhoneNo the alternate phone number to set
     */
    public void setAlternatePhoneNo(String alternatePhoneNo) {
        this.alternatePhoneNo = alternatePhoneNo;
    }

    /**
     * Returns the account status of the user.
     *
     * @return the status as a {@link String}
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the account status of the user.
     *
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the timestamp of the user's last successful login.
     *
     * @return the last login timestamp as a {@link Timestamp}
     */
    public Timestamp getLastLogin() {
        return lastLogin;
    }

    /**
     * Sets the timestamp of the user's last successful login.
     *
     * @param lastLogin the last login timestamp to set
     */
    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * Returns the count of consecutive unsuccessful login attempts.
     *
     * @return the unsuccessful login attempt count as an {@link Integer}
     */
    public Integer getUnsucessfullLoginAttempt() {
        return unsucessfullLoginAttempt;
    }

    /**
     * Sets the count of consecutive unsuccessful login attempts.
     *
     * @param unsucessfullLoginAttempt the count to set
     */
    public void setUnsucessfullLoginAttempt(Integer unsucessfullLoginAttempt) {
        this.unsucessfullLoginAttempt = unsucessfullLoginAttempt;
    }

    /**
     * Returns the ID of the profile picture attachment associated with this user.
     *
     * @return the image ID as a {@link Long}
     */
    public Long getImageId() {
        return imageId;
    }

    /**
     * Sets the ID of the profile picture attachment associated with this user.
     *
     * @param imageId the image ID to set
     */
    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    /**
     * Returns the full name of the user by concatenating first and last name.
     *
     * @return the full name as a {@link String}
     */
    public String getName() {
        return firstName + " " + lastName;
    }

    /**
     * Returns the display value for dropdown list usage.
     *
     * @return the full name of the user as a {@link String}
     */
    @Override
    public String getValue() {
        // TODO Auto-generated method stub
        return getName();
    }

    /**
     * Returns the unique key attribute name used for duplicate checking.
     *
     * @return {@code "login"} as the unique key attribute
     */
    @Override
    public String getUniqueKey() {
        // TODO Auto-generated method stub
        return "login";
    }

    /**
     * Returns the value of the unique key attribute for this entity instance.
     *
     * @return the login ID as the unique key value
     */
    @Override
    public String getUniqueValue() {
        // TODO Auto-generated method stub
        return login;
    }

    /**
     * Returns the human-readable label for this entity.
     *
     * @return {@code "Login Id"} as the display label
     */
    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return "Login Id";
    }

    /**
     * Returns the logical table name associated with this entity.
     *
     * @return {@code "User"} as the table name
     */
    @Override
    public String getTableName() {
        // TODO Auto-generated method stub
        return "User";
    }
}