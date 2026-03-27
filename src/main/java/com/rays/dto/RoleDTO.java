package com.rays.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "ST_ROLE")
public class RoleDTO extends BaseDTO {

	@Column(name = "NAME", length = 50)
	private String name;
	
	@Column(name = "DESCRIPTION", length = 50)
	private String description;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}
	@Override
	public String getUniqueKey() {
		// TODO Auto-generated method stub
		return "name";
	}
	@Override
	public String getUniqueValue() {
		// TODO Auto-generated method stub
		return name;
	}
	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Role Name";
	}
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "ROLE";
	}
	@Override
	public String toString() {
		return "RoleDTO [name=" + name + ", description=" + description + "]";
	}
	
	
	
	
}
