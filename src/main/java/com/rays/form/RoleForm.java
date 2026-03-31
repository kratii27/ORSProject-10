package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.RoleDTO;

/**
 * Form class for handling role operations.
 * This class captures role details including role name and description
 * for role management functionality. Extends BaseForm with RoleDTO as the DTO type.
 * 
 * @author Krati Trivedi
 */
public class RoleForm extends BaseForm<RoleDTO> {
	
	@NotEmpty(message = "Role name is required")
	private String name;
	
	@NotEmpty(message = "Description is required")
	private String description;
	
	/**
	 * Gets the name of the role.
	 * 
	 * @return the role name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the role.
	 * 
	 * @param name the role name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the description of the role.
	 * 
	 * @return the role description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description of the role.
	 * 
	 * @param description the role description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Converts this form object to a RoleDTO object.
	 * Initializes a new DTO, populates it with role name and description,
	 * and returns the populated DTO.
	 * 
	 * @return the populated RoleDTO object
	 */
	public RoleDTO getDto() {
		RoleDTO dto = (RoleDTO) initDTO(new RoleDTO());
		dto.setName(name);
		dto.setDescription(description);
		return dto;
	}
	

}