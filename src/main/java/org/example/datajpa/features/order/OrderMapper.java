package org.example.datajpa.features.order;

import org.example.datajpa.features.order.dto.OrderRequest;
import org.example.datajpa.features.order.dto.OrderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toOrderResponse(Order order);
    Order toOrder(OrderRequest orderRequest);
}
