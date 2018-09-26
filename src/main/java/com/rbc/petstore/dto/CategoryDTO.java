package com.rbc.petstore.dto;

import javax.validation.constraints.NotEmpty;

public class CategoryDTO {

	private Long id;

	@NotEmpty
	private String name;

	public CategoryDTO() {

	}

	public CategoryDTO(Long id, @NotEmpty String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
