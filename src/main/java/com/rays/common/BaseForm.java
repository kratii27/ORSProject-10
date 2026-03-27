package com.rays.common;

public class BaseForm<T extends BaseDTO> {

	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public T getDto() {
		return null;
	}

	public T initDTO(T dto) {
		if (id != null && id > 0) {
			dto.setId(id);
		} else {
			dto.setId(null);
		}
		return dto;
	}
}
