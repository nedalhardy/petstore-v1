package com.rbc.petstore.mapper;

import org.mapstruct.Mapper;

import com.rbc.petstore.dto.OrderDTO;
import com.rbc.petstore.model.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO sourceToDestination(Order source);
    Order destinationToSource(OrderDTO destination);
}
