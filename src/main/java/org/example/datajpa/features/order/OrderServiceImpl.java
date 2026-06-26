package org.example.datajpa.features.order;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.datajpa.features.order.dto.OrderLineDto;
import org.example.datajpa.features.order.dto.OrderRequest;
import org.example.datajpa.features.order.dto.OrderResponse;
import org.example.datajpa.features.orderLine.OrderLine;
import org.example.datajpa.features.orderLine.OrderLineRepository;
import org.example.datajpa.features.product.Product;
import org.example.datajpa.features.product.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderLineRepository orderLineRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {

        List<OrderLine> orderLines = new ArrayList<>();
        final Order order = orderMapper.toOrder(orderRequest);
        boolean isValidTrue = orderRequest.orderLine().stream()
                .allMatch(orderLineDto -> {
                    Optional<Product> productOptional = productRepository.findByCode(orderLineDto.code());
                    if(productOptional.isPresent()){
                        OrderLine orderLine = new OrderLine();
                        orderLine.setProduct(productOptional.get());
                        orderLine.setQty(orderLineDto.qty());
                        orderLine.setUnitPrice(orderLineDto.unitPrice());
                        orderLine.setOrder(order);
                        orderLines.add(orderLine);
                        return true;
                    }
                    return false;
                });
        if(!isValidTrue){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid order line.");
        }
        order.setCustomerId("ISTAD");
        order.setIsDelete(false);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(false);
        order.setOrderLines(orderLines);
        Order orders = orderRepository.save(order);
        return orderMapper.toOrderResponse(orders);
    }
}
