package com.rbc.petstore.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.rbc.petstore.model.InventoryStatus;
import com.rbc.petstore.model.Pet;

/**
 * {@link Pet} CRUD operations
 */
public interface PetRepository extends CrudRepository<Pet, Long> {
	
	@Query( "select o from Pet o where status in :status" )
	List<Pet> findByStatusList(@Param("status") List<InventoryStatus> status);
	
	@Query("select new map(v.status as status, count(v) as count) from Pet v group by v.status")
	public List<Map<Object,Object>> findByPetAndQuantity();
}
