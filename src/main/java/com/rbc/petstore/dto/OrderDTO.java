package com.rbc.petstore.dto;

import java.util.Date;

import com.rbc.petstore.model.OrderStatus;

public class OrderDTO {

	public OrderDTO() {

	}

	public OrderDTO(Long id, Long pet, Integer quantity, OrderStatus status, Date shipDate) {
		super();
		this.id = id;
		this.petId = pet;
		this.quantity = quantity;
		this.status = status;
		this.shipDate = shipDate;
	}

	private Long id;

	private Long petId;

	private Integer quantity;

	private OrderStatus status;

	private Date shipDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPetId() {
		return petId;
	}

	public void setPetId(Long petId) {
		this.petId = petId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

}
