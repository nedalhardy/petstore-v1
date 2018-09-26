package com.rbc.petstore.service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rbc.petstore.dto.PetDTO;
import com.rbc.petstore.exceptions.ResourceNotFound;
import com.rbc.petstore.model.InventoryStatus;
import com.rbc.petstore.model.Pet;
import com.rbc.petstore.repository.PetRepository;

/**
 * Pet Service
 *
 * @see PetDTO
 */
@Service
public class PetService {

    @Autowired
    PetRepository petRepository;
    
    @Autowired
    FileStorageService fileStorageService;


    /**
     * Get all Pets
     *
     * @return a pets
     */
    public Iterable<Pet> getAllPets() {
        return petRepository.findAll();
    }


    /**
     * Find pet by ID
     *
     * @param id identifier of the pet to find
     * @return a {@link Pet} instance if a match is found, null otherwise
     */
    public Pet getPet(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    /**
     * Add a new pet to the store
     *
     * @param pet entity to create
     * @return the created {@link Pet}
     */
    public Pet createPet(Pet pet) {
      /*  if (pet.getId() != null) {
            throw new InvalidParameterException("Cannot create an already existing pet");
        }*/
        return petRepository.save(pet);
    }

    /**
     * Deletes a pet
     *
     * @param id Pet id to delete
     */
    public void deletePet(Long id) {
        Optional<Pet> toDelete = petRepository.findById(id);
        if (!toDelete.isPresent()) {
            throw new InvalidParameterException("Pet not found");
        }

        petRepository.deleteById(id);
    }
    
    public List<Pet> findByStatus (List<InventoryStatus> status) {
    	return this.petRepository.findByStatusList(status);
    }
    
    public Pet updateFormData(Long id, String name, InventoryStatus status) throws ResourceNotFound {
    	Optional<Pet> pet = null;
    	if((pet = this.petRepository.findById(id)) == null){
    		throw new ResourceNotFound("Pet not found");
    	}
    	pet.get().setName(name);
    	pet.get().setStatus(status);
    	return this.createPet(pet.get());
    }
    
    public Pet uploadImage(Long id, MultipartFile file) throws ResourceNotFound {
    	Optional<Pet> pet = null;
    	if((pet = this.petRepository.findById(id)) == null){
    		throw new ResourceNotFound("Pet not found");
    	}
    	String fileName = fileStorageService.storeFile(file);
    	pet.get().getPhotoUrls().add(fileName);
    	return this.createPet(pet.get());
    }
    
	public List<Map<Object,Object>> findByPetAndQuantity() {
		return this.petRepository.findByPetAndQuantity();
	}
}
