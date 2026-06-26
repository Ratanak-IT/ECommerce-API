package org.example.datajpa.features.order;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.datajpa.features.order.dto.OrderRequest;
import org.example.datajpa.features.order.dto.OrderResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponse createOrder(@Valid  @RequestBody OrderRequest orderRequest){
        return orderService.createOrder(orderRequest);
    }
}
