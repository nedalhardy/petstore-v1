package com.rbc.petstore.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbc.petstore.dto.UserDTO;
import com.rbc.petstore.mapper.UserMapper;
import com.rbc.petstore.model.User;
import com.rbc.petstore.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/user")
public class UserResource {

	private final UserService userService;

	private final UserMapper userMapper;

	@Autowired
	public UserResource(UserService userService, UserMapper userMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
	}

	@PostMapping("/sign-up")
	public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO) {
		try {
			User user = userMapper.destinationToSource(userDTO);
			User userCreated = userService.createUser(user);
			return new ResponseEntity<>(userMapper.sourceToDestination(userCreated), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/createWithList")
	public ResponseEntity<List<UserDTO>> createWithList(@Valid @RequestBody List<UserDTO> usersDTO) {
		try {
			List<User> users = userMapper.ListdestinationToSource(usersDTO);
			List<User> usersCreated = userService.createWithList(users);

			return new ResponseEntity<>(userMapper.ListsourceToDestination(usersCreated), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/createWithArray")
	public ResponseEntity<List<UserDTO>> createWithArray(@Valid @RequestBody List<UserDTO> usersDTO) {
		try {
			List<User> users = userMapper.ListdestinationToSource(usersDTO);
			List<User> usersCreated = userService.createWithArray((User[]) users.toArray());

			return new ResponseEntity<>(userMapper.ListsourceToDestination(usersCreated), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{username}")
	public ResponseEntity<UserDTO> findByUsername(@PathVariable(name = "username") String username) {
		User user = userService.findByUserName(username);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(userMapper.sourceToDestination(user), HttpStatus.OK);
	}

	@PutMapping("/{username}")
	public ResponseEntity<UserDTO> update(@PathVariable(name = "username") String username,
			@Valid @RequestBody UserDTO userDTO) {
		try {
			User user = userMapper.destinationToSource(userDTO);
			User userCreated = userService.update(username, user);

			return new ResponseEntity<>(userMapper.sourceToDestination(userCreated), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{username}")
	public ResponseEntity<Void> delete(@PathVariable(name = "username") String username) {
		try {
			userService.delete(username);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
