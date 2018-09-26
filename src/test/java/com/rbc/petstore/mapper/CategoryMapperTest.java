package com.rbc.petstore.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mapstruct.factory.Mappers;

import com.rbc.petstore.dto.CategoryDTO;
import com.rbc.petstore.model.Category;

public class CategoryMapperTest {

	private CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

	@Test
	public void givenSourceToDestination_whenMaps_thenCorrect() {
		Category category = new Category();
		category.setId(100l);
		category.setName("SourceName");
		CategoryDTO destination = mapper.sourceToDestination(category);
		assertEquals(category.getName(), destination.getName());
		assertEquals(category.getId(), destination.getId());
	}

	@Test
	public void givenDestinationToSource_whenMaps_thenCorrect() {
		CategoryDTO destination = new CategoryDTO();
		destination.setName("DestinationName");
		destination.setId(100l);
		Category source = mapper.destinationToSource(destination);
		assertEquals(destination.getName(), source.getName());
		assertEquals(destination.getId(), source.getId());
	}
}
