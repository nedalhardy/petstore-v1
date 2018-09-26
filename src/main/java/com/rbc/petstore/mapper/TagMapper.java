package com.rbc.petstore.mapper;

import org.mapstruct.Mapper;

import com.rbc.petstore.dto.TagDTO;
import com.rbc.petstore.model.Tag;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDTO sourceToDestination(Tag source);
    Tag destinationToSource(TagDTO destination);
}
