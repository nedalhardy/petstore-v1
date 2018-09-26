package com.rbc.petstore.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbc.petstore.dto.OrderDTO;
import com.rbc.petstore.dto.PetDTO;
import com.rbc.petstore.mapper.OrderMapper;
import com.rbc.petstore.model.Order;
import com.rbc.petstore.service.OrderService;
import com.rbc.petstore.service.PetService;

/**
 * REST operations for {@link PetDTO} resource
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/store")
public class StoreResource {

	private final PetService petService;
	
	private final OrderMapper orderMapper;
	
	private final OrderService orderService;

	@Autowired
	public StoreResource(PetService petService, OrderMapper orderMapper, OrderService orderService) {
		this.petService = petService;
		this.orderMapper = orderMapper;
		this.orderService = orderService;
	}

	@GetMapping("/inventory")
	public ResponseEntity<List<Map<Object, Object>>> findByStatus() {
		List<Map<Object, Object>> pets = petService.findByPetAndQuantity();
		return new ResponseEntity<>(pets, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/order")
	public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO orderDTO) {
		try {
			Order order = orderMapper.destinationToSource(orderDTO);
			Order createdOrder = orderService.create(order);

			return new ResponseEntity<>(orderMapper.sourceToDestination(createdOrder), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<OrderDTO> getOne(@PathVariable(name = "orderId") Long orderId) {
		Order order = orderService.getOrder(orderId);
		if (order == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(orderMapper.sourceToDestination(order), HttpStatus.OK);
	}
	
	@DeleteMapping("/order/{orderId}")
	public ResponseEntity<Void> delete(@PathVariable(name = "orderId") Long orderId) {
		try {
			orderService.deleteOrder(orderId);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
