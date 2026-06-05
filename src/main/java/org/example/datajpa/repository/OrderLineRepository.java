package org.example.datajpa.repository;


import org.example.datajpa.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer>{

}
