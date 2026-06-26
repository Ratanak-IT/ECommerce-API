package org.example.datajpa.features.order;

import org.example.datajpa.features.order.dto.OrderRequest;
import org.example.datajpa.features.order.dto.OrderResponse;
import org.example.datajpa.features.order.dto.SoftDeleteRequest;
import org.example.datajpa.features.order.dto.UpdatePaymentRequest;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderResponse createOrder(OrderRequest orderRequest);
    Page<OrderResponse> getAllOrder(int page, int size);
    OrderResponse getOrderById(UUID id);
    void softDeleteById(UUID id, SoftDeleteRequest softDeleteRequest);
    void hardDeleteById(UUID id);
    OrderResponse setPaymentStatusById(UUID id, UpdatePaymentRequest updatePaymentRequest);
}
