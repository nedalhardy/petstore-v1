package com.rbc.petstore.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import com.rbc.petstore.dto.UserDTO;
import com.rbc.petstore.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserDTO sourceToDestination(User source);

	User destinationToSource(UserDTO destination);

	List<UserDTO> ListsourceToDestination(List<User> source);

	List<User> ListdestinationToSource(List<UserDTO> destination);
}
