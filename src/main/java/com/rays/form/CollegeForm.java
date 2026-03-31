package com.rays.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.rays.common.BaseForm;
import com.rays.dto.CollegeDTO;

/**
 * Form class for handling College add and update requests in the ORS application.
 * <p>
 * Extends {@link BaseForm} parameterized with {@link CollegeDTO} and carries
 * college-related input fields submitted from the client. Validation constraints
 * are applied to ensure all required fields are provided in the correct format.
 * </p>
 *
 * @author Krati Trivedi
 */
public class CollegeForm extends BaseForm<CollegeDTO> {

    /**
     * The name of the college. Must not be empty.
     */
    @NotEmpty(message = "Name is required")
    private String name;

    /**
     * The address of the college. Must not be empty.
     */
    @NotEmpty(message = "Address is required")
    private String address;

    /**
     * The state in which the college is located. Must not be empty.
     */
    @NotEmpty(message = "State is required")
    private String state;

    /**
     * The city in which the college is located. Must not be empty.
     */
    @NotEmpty(message = "City is required")
    private String city;

    /**
     * The contact phone number of the college. Must not be null and
     * must be exactly 10 digits or empty.
     */
    @NotNull(message = "Phone No is required")
    @Pattern(regexp = "(^$|[0-9]{10})")
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
     * Constructs and returns a {@link CollegeDTO} populated with the form's field values.
     * <p>
     * Initializes the DTO using {@link #initDTO(com.rays.common.BaseDTO)} to set
     * the ID appropriately for add or update operations, then maps all form
     * fields onto the DTO.
     * </p>
     *
     * @return a fully populated {@link CollegeDTO} instance
     */
    @Override
    public CollegeDTO getDto() {
        CollegeDTO dto = initDTO(new CollegeDTO());
        dto.setName(name);
        dto.setAddress(address);
        dto.setState(state);
        dto.setCity(city);
        dto.setPhoneNo(phoneNo);
        return dto;
    }
}