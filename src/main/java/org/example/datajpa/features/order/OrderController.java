package org.example.datajpa.features.order;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.datajpa.features.order.dto.OrderRequest;
import org.example.datajpa.features.order.dto.OrderResponse;
import org.example.datajpa.features.order.dto.SoftDeleteRequest;
import org.example.datajpa.features.order.dto.UpdatePaymentRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponse createOrder(@Valid  @RequestBody OrderRequest orderRequest){
        return orderService.createOrder(orderRequest);
    }

    @GetMapping
    public Page<OrderResponse> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {
        return orderService.getAllOrder(page, size);
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable UUID id) {
        return orderService.getOrderById(id);
    }

    @PutMapping("/{id}/soft-delete")
    public void softDelete(
            @PathVariable UUID id,
            @RequestBody SoftDeleteRequest request
    ) {
        orderService.softDeleteById(id, request);
    }

    @DeleteMapping("/{id}")
    public void hardDelete(@PathVariable UUID id) {
        orderService.hardDeleteById(id);
    }

    @PutMapping("/{id}/status")
    public OrderResponse setPaymentStatus(
            @PathVariable UUID id,
            @RequestBody UpdatePaymentRequest request
    ) {
        return orderService.setPaymentStatusById(id, request);
    }

}
