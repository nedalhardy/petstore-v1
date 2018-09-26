package com.rbc.petstore.service;

import java.security.InvalidParameterException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbc.petstore.dto.PetDTO;
import com.rbc.petstore.exceptions.ResourceNotFound;
import com.rbc.petstore.model.Order;
import com.rbc.petstore.repository.OrderRepository;

/**
 * Pet Service
 *
 * @see PetDTO
 */
@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	PetService petService;

	public Order create(Order order) {
		if (petService.getPet(order.getPetId()) == null) {
			throw new ResourceNotFound("Pet not found");
		}
		return orderRepository.save(order);
	}

	public Order getOrder(Long id) {
		return orderRepository.findById(id).orElse(null);
	}

	public void deleteOrder(Long id) {
		Optional<Order> toDelete = orderRepository.findById(id);
		if (!toDelete.isPresent()) {
			throw new InvalidParameterException("Pet not found");
		}

		orderRepository.deleteById(id);
	}

}
