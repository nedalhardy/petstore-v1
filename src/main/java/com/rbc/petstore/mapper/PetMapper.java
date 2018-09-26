package com.rbc.petstore.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.rbc.petstore.dto.PetDTO;
import com.rbc.petstore.model.Pet;

@Mapper(componentModel = "spring")
public interface PetMapper {

	PetDTO sourceToDestination(Pet source);

	Pet destinationToSource(PetDTO destination);

	List<PetDTO> ListsourceToDestination(List<Pet> source);

	List<Pet> ListdestinationToSource(List<PetDTO> destination);

}
