package com.rbc.petstore.rest;

import com.rbc.petstore.dto.PetDTO;
import com.rbc.petstore.mapper.PetMapper;
import com.rbc.petstore.model.InventoryStatus;
import com.rbc.petstore.model.Pet;
import com.rbc.petstore.service.PetService;
import com.rbc.petstore.util.PetConvertor;

import convertor.StringToInventoryStatusConverter;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.util.List;

/**
 * REST operations for {@link PetDTO} resource
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/pet")
public class PetResource {

	private final PetService petService;

	private final PetMapper petMapper;

	@Autowired
	public PetResource(PetService petService, PetMapper petMapper) {
		this.petService = petService;
		this.petMapper = petMapper;
	}

	@InitBinder
	protected void initBinde_r(WebDataBinder binder) {
		binder.registerCustomEditor(List.class, new StringToInventoryStatusConverter());
	}

	/**
	 * Get all pets
	 *
	 * @return a {@link HttpStatus#OK} on the status and a PetDTO instance in the
	 *         response body if the id exists, {@link HttpStatus#BAD_REQUEST} on
	 *         status otherwise
	 */
	@GetMapping("")
	public ResponseEntity<List<PetDTO>> getAll() {
		Iterable<Pet> pets = petService.getAllPets();
		List<Pet> target = IteratorUtils.toList(pets.iterator());
		return new ResponseEntity<>(petMapper.ListsourceToDestination(target), HttpStatus.OK);
	}

	/**
	 * Get one pet by id.
	 *
	 * @param petId
	 *            pet identifier
	 * @return a {@link HttpStatus#OK} on the status and a PetDTO instance in the
	 *         response body if the id exists, {@link HttpStatus#BAD_REQUEST} on
	 *         status otherwise
	 */
	@GetMapping("/{petId}")
	public ResponseEntity<PetDTO> getOne(@PathVariable(name = "petId") Long petId) {
		Pet pet = petService.getPet(petId);
		if (pet == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(petMapper.sourceToDestination(pet), HttpStatus.OK);
	}

	/**
	 * Find By Status
	 *
	 * @return a {@link HttpStatus#OK} return list of pets based on a list of status
	 */
	@GetMapping("/findByStatus")
	public ResponseEntity<List<PetDTO>> findByStatus(@RequestParam(value = "status") List<InventoryStatus> status) {
		List<Pet> pets = petService.findByStatus(status);
		if (pets == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(petMapper.ListsourceToDestination(pets), HttpStatus.OK);
	}

	/**
	 * Add a new pet to the store
	 *
	 * @param petDto
	 *            to create
	 * @return if successfully it returns {@link HttpStatus#CREATED} on status and
	 *         the created {@link PetDTO}, {@link HttpStatus#BAD_REQUEST} on status
	 *         otherwise
	 */
	@PostMapping("")
	public ResponseEntity<PetDTO> create(@Valid @RequestBody PetDTO petDto) {
		try {
			Pet pet = petMapper.destinationToSource(petDto);
			Pet createdPet = petService.createPet(pet);

			return new ResponseEntity<>(petMapper.sourceToDestination(createdPet), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Deletes a pet
	 *
	 * @param petId
	 *            pet identifier
	 * @return if successfully a {@link HttpStatus#OK} on the status,
	 *         {@link HttpStatus#BAD_REQUEST} otherwise
	 */
	@DeleteMapping("/{petId}")
	public ResponseEntity<Void> delete(@PathVariable(name = "petId") Long petId) {
		try {
			petService.deletePet(petId);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Update Pet
	 *
	 * @return a {@link HttpStatus#OK} update existing pet
	 */
	@PutMapping("")
	public ResponseEntity<PetDTO> update(@Valid @RequestBody PetDTO petDto) {
		try {
			Pet pet = PetConvertor.fromDto(petDto);
			Pet createdPet = petService.createPet(pet);

			return new ResponseEntity<>(petMapper.sourceToDestination(createdPet), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Create PET
	 *
	 * @return a {@link HttpStatus#OK} update pet based on form data informations
	 */

	@PostMapping("/{petId}")
	public ResponseEntity<PetDTO> updateformData(@PathVariable(name = "petId") Long petId, @RequestParam String name,
			@RequestParam InventoryStatus status) {

		try {
			Pet pet = this.petService.updateFormData(petId, name, status);
			return new ResponseEntity<>(petMapper.sourceToDestination(pet), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Upload an image for pet
	 *
	 * @return a {@link HttpStatus#OK} upload a new image for a pet
	 */
	
	@PostMapping("/{petId}/uploadImage")
	public ResponseEntity<PetDTO> uploadImage(@PathVariable(name = "petId") Long petId,
			@RequestParam(required = false) String additionalMetadata, @RequestParam MultipartFile file) {
		try {
			Pet pet = this.petService.uploadImage(petId, file);
			return new ResponseEntity<>(petMapper.sourceToDestination(pet), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
