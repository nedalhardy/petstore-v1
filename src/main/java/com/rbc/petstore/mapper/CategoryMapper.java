package com.rbc.petstore.mapper;

import org.mapstruct.Mapper;

import com.rbc.petstore.dto.CategoryDTO;
import com.rbc.petstore.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO sourceToDestination(Category source);
    Category destinationToSource(CategoryDTO destination);
}
