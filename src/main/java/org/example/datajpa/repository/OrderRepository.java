package org.example.datajpa.repository;

import org.example.datajpa.domain.Order;
import org.example.datajpa.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {



}
