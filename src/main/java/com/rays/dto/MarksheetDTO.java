package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "ST_MARKSHEET")
public class MarksheetDTO extends BaseDTO {
	
	@Column(name = "ROLL_NO", length = 50)
	private String rollNo = null;
	
	@Column(name = "STUDENT_ID")
	private Long studentId;
	
	@Column(name = "NAME", length = 50)
	private String name = null;
	
	@Column(name = "PHYSICS")
	private Integer physics;
	
	@Column(name = "CHEMISTRY")
	private Integer chemistry;
	
	@Column(name = "MATHS")
	private Integer maths;

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPhysics() {
		return physics;
	}

	public void setPhysics(Integer physics) {
		this.physics = physics;
	}

	public Integer getChemistry() {
		return chemistry;
	}

	public void setChemistry(Integer chemistry) {
		this.chemistry = chemistry;
	}

	public Integer getMaths() {
		return maths;
	}

	public void setMaths(Integer maths) {
		this.maths = maths;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUniqueKey() {
		// TODO Auto-generated method stub
		return "rollNo";
	}

	@Override
	public String getUniqueValue() {
		// TODO Auto-generated method stub
		return rollNo;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Roll No";
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "Marksheet";
	}

}
