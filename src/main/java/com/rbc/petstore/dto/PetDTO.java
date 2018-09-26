package com.rbc.petstore.dto;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import com.rbc.petstore.model.InventoryStatus;

/**
 * Pet Data Transfer Object
 */
public class PetDTO {

	private Long id;

	@NotEmpty
	private String name;

	private InventoryStatus status;

	private CategoryDTO category;

	private List<String> photoUrls = new ArrayList<String>();

	private List<TagDTO> tags;

	public PetDTO() {
		// default constructor, nothing here for now
	}

	public PetDTO(Long id, String name, InventoryStatus status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}

	public PetDTO(Long id, String name, InventoryStatus status, CategoryDTO category) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.category = category;
	}
	

	public PetDTO(Long id, @NotEmpty String name, InventoryStatus status, CategoryDTO category, List<String> photoUrls,
			List<TagDTO> tags) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.category = category;
		this.photoUrls = photoUrls;
		this.tags = tags;
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

	public InventoryStatus getStatus() {
		return status;
	}

	public void setStatus(InventoryStatus status) {
		this.status = status;
	}

	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	public List<TagDTO> getTags() {
		return tags;
	}

	public void setTags(List<TagDTO> tags) {
		this.tags = tags;
	}

}
