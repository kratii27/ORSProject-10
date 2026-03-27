package com.rays.common;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class BaseDTO implements DropdownList {

	@Id
	@GeneratedValue(generator = "pk")
	@GenericGenerator(name = "pk", strategy = "native")
	@Column(name = "ID", nullable = false, unique = true)
	protected Long id;

	@Column(name = "MODIFIED_BY", length = 50)
	protected String modifiedBy = "root";

	@Column(name = "CREATED_BY", length = 50)
	protected String createdBy = "root";

	@Column(name = "CREATED_DATE_TIME")
	protected Timestamp createdDateTime;

	@Column(name = "MODIFIED_DATE_TIME")
	protected Timestamp modifiedDateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Timestamp createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Timestamp getModifiedDateTime() {
		return modifiedDateTime;
	}

	public void setModifiedDateTime(Timestamp modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return String.valueOf(id);
	}
	
	public abstract String getUniqueKey();
	public abstract String getUniqueValue();
	public abstract String getLabel();
	public abstract String getTableName();

}
