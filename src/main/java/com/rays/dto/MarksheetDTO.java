package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

/**
 * Data Transfer Object (DTO) representing a Marksheet entity in the ORS application.
 * <p>
 * Maps to the {@code ST_MARKSHEET} database table and holds marksheet-related
 * information such as roll number, student ID, student name, and marks obtained
 * in Physics, Chemistry, and Mathematics.
 * </p>
 *
 * @author Krati Trivedi
 */
@Entity
@Table(name = "ST_MARKSHEET")
public class MarksheetDTO extends BaseDTO {

    /**
     * The roll number of the student associated with this marksheet.
     */
    @Column(name = "ROLL_NO", length = 50)
    private String rollNo = null;

    /**
     * The ID of the student associated with this marksheet.
     */
    @Column(name = "STUDENT_ID")
    private Long studentId;

    /**
     * The full name of the student associated with this marksheet.
     */
    @Column(name = "NAME", length = 50)
    private String name = null;

    /**
     * The marks obtained by the student in Physics.
     */
    @Column(name = "PHYSICS")
    private Integer physics;

    /**
     * The marks obtained by the student in Chemistry.
     */
    @Column(name = "CHEMISTRY")
    private Integer chemistry;

    /**
     * The marks obtained by the student in Mathematics.
     */
    @Column(name = "MATHS")
    private Integer maths;

    /**
     * Returns the roll number of the student.
     *
     * @return the roll number as a {@link String}
     */
    public String getRollNo() {
        return rollNo;
    }

    /**
     * Sets the roll number of the student.
     *
     * @param rollNo the roll number to set
     */
    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    /**
     * Returns the ID of the student associated with this marksheet.
     *
     * @return the student ID as a {@link Long}
     */
    public Long getStudentId() {
        return studentId;
    }

    /**
     * Sets the ID of the student associated with this marksheet.
     *
     * @param studentId the student ID to set
     */
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    /**
     * Returns the full name of the student associated with this marksheet.
     *
     * @return the student name as a {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the full name of the student associated with this marksheet.
     *
     * @param name the student name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the marks obtained by the student in Physics.
     *
     * @return the Physics marks as an {@link Integer}
     */
    public Integer getPhysics() {
        return physics;
    }

    /**
     * Sets the marks obtained by the student in Physics.
     *
     * @param physics the Physics marks to set
     */
    public void setPhysics(Integer physics) {
        this.physics = physics;
    }

    /**
     * Returns the marks obtained by the student in Chemistry.
     *
     * @return the Chemistry marks as an {@link Integer}
     */
    public Integer getChemistry() {
        return chemistry;
    }

    /**
     * Sets the marks obtained by the student in Chemistry.
     *
     * @param chemistry the Chemistry marks to set
     */
    public void setChemistry(Integer chemistry) {
        this.chemistry = chemistry;
    }

    /**
     * Returns the marks obtained by the student in Mathematics.
     *
     * @return the Mathematics marks as an {@link Integer}
     */
    public Integer getMaths() {
        return maths;
    }

    /**
     * Sets the marks obtained by the student in Mathematics.
     *
     * @param maths the Mathematics marks to set
     */
    public void setMaths(Integer maths) {
        this.maths = maths;
    }

    /**
     * Returns the display value for dropdown list usage.
     * <p>
     * Not applicable for marksheets; always returns {@code null}.
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
     * @return {@code "rollNo"} as the unique key attribute
     */
    @Override
    public String getUniqueKey() {
        // TODO Auto-generated method stub
        return "rollNo";
    }

    /**
     * Returns the value of the unique key attribute for this entity instance.
     *
     * @return the roll number as the unique key value
     */
    @Override
    public String getUniqueValue() {
        // TODO Auto-generated method stub
        return rollNo;
    }

    /**
     * Returns the human-readable label for this entity.
     *
     * @return {@code "Roll No"} as the display label
     */
    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return "Roll No";
    }

    /**
     * Returns the logical table name associated with this entity.
     *
     * @return {@code "Marksheet"} as the table name
     */
    @Override
    public String getTableName() {
        // TODO Auto-generated method stub
        return "Marksheet";
    }
}