package com.rbc.petstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.rbc.petstore.model.Order;
import com.rbc.petstore.model.User;

/**
 * {@link User} CRUD operations
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
	
}
