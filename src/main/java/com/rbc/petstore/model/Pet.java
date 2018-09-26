package com.rbc.petstore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

/**
 * Pet class holding the pet entity information.
 */
@Entity
public class Pet {
	public Pet() {
		// nothing to do here
	}

	public Pet(Long id, @NotEmpty String name, InventoryStatus status) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
	}

	public Pet(Long id, @NotEmpty String name, InventoryStatus status, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.category = category;
	}

	public Pet(Long id, @NotEmpty String name, InventoryStatus status, List<String> photoUrls, List<Tag> tags,
			Category category) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.photoUrls = photoUrls;
		this.tags = tags;
		this.category = category;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PET_ID")
	private Long id;

	@NotEmpty
	private String name;

	@Enumerated(EnumType.STRING)
	private InventoryStatus status;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "PHOTOS", joinColumns = @JoinColumn(name = "PET_ID"))
	@Column(name="name")
	private List<String> photoUrls = new ArrayList<String>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "PET_ID")
	private List<Tag> tags;

	@ManyToOne(optional = true)
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;

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

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
