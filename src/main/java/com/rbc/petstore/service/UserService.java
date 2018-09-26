package com.rbc.petstore.service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rbc.petstore.dto.PetDTO;
import com.rbc.petstore.exceptions.ResourceNotFound;
import com.rbc.petstore.model.User;
import com.rbc.petstore.repository.UserRepository;

/**
 * Pet Service
 *
 * @see PetDTO
 */
@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public User getUser(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public User createUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public List<User> createWithArray(User[] users) {
		List<User> usersCreated = new ArrayList<User>();
		for (User user : users) {
			usersCreated.add(createUser(user));
		}
		return usersCreated;
	}

	public List<User> createWithList(List<User> users) {
		return this.createWithArray(users.toArray(new User[users.size()]));
	}

	public User findByUserName(String username) {
		return this.userRepository.findByUsername(username);
	}

	public void deleteUser(Long id) {
		Optional<User> toDelete = userRepository.findById(id);
		if (!toDelete.isPresent()) {
			throw new InvalidParameterException("User not found");
		}

		userRepository.deleteById(id);
	}

	public User update(String username, User userToUpdate) {
		if (this.userRepository.findByUsername(username) == null) {
			throw new ResourceNotFound("Pet not found");
		}
		return userRepository.save(userToUpdate);
	}

	public void delete(String username) {
		User user = null;
		if ((user = this.userRepository.findByUsername(username)) == null) {
			throw new ResourceNotFound("User not found");
		}
		userRepository.deleteById(user.getId());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return (UserDetails) new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(), new ArrayList<>());
	}

}
