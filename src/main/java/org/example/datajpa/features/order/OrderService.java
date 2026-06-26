package org.example.datajpa.features.order;

import org.example.datajpa.features.order.dto.OrderRequest;
import org.example.datajpa.features.order.dto.OrderResponse;

public interface OrderService {

    OrderResponse createOrder(OrderRequest orderRequest);
}
