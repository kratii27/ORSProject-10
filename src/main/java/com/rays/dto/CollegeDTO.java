package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Data Transfer Object (DTO) representing a College entity in the ORS application.
 * <p>
 * Maps to the {@code ST_COLLEGE} database table and holds college-related
 * information such as name, address, state, city, and phone number.
 * </p>
 *
 * @author Krati Trivedi
 */
@Entity
@Table(name = "ST_COLLEGE")
public class CollegeDTO extends BaseDTO {

    /**
     * The name of the college.
     */
    @Column(name = "NAME", length = 50)
    private String name;

    /**
     * The address of the college.
     */
    @Column(name = "ADDRESS", length = 50)
    private String address;

    /**
     * The state in which the college is located.
     */
    @Column(name = "STATE", length = 50)
    private String state;

    /**
     * The city in which the college is located.
     */
    @Column(name = "CITY", length = 50)
    private String city;

    /**
     * The contact phone number of the college.
     */
    @Column(name = "PHONE_NO", length = 50)
    private String phoneNo;

    /**
     * Returns the name of the college.
     *
     * @return the college name as a {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the college.
     *
     * @param name the college name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the address of the college.
     *
     * @return the address as a {@link String}
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the college.
     *
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the state in which the college is located.
     *
     * @return the state as a {@link String}
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state in which the college is located.
     *
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Returns the city in which the college is located.
     *
     * @return the city as a {@link String}
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city in which the college is located.
     *
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the contact phone number of the college.
     *
     * @return the phone number as a {@link String}
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * Sets the contact phone number of the college.
     *
     * @param phoneNo the phone number to set
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * Returns the display value for dropdown list usage.
     *
     * @return the college name as a {@link String}
     */
    @Override
    public String getValue() {
        // TODO Auto-generated method stub
        return name;
    }

    /**
     * Returns the unique key attribute name used for duplicate checking.
     *
     * @return {@code "name"} as the unique key attribute
     */
    @Override
    public String getUniqueKey() {
        // TODO Auto-generated method stub
        return "name";
    }

    /**
     * Returns the value of the unique key attribute for this entity instance.
     *
     * @return the college name as the unique key value
     */
    @Override
    public String getUniqueValue() {
        // TODO Auto-generated method stub
        return name;
    }

    /**
     * Returns the human-readable label for this entity.
     *
     * @return {@code "college"} as the display label
     */
    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return "college";
    }

    /**
     * Returns the logical table name associated with this entity.
     *
     * @return {@code "College"} as the table name
     */
    @Override
    public String getTableName() {
        // TODO Auto-generated method stub
        return "College";
    }
}