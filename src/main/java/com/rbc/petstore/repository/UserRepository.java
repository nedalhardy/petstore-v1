package com.rbc.petstore.repository;

import org.springframework.data.repository.CrudRepository;
import com.rbc.petstore.model.User;

/**
 * {@link User} CRUD operations
 */
public interface UserRepository extends CrudRepository<User, Long> {
	
	public User findByUsername(String username);
}
