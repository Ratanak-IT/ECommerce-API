package org.example.datajpa.features.order;

import org.example.datajpa.features.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {


}
