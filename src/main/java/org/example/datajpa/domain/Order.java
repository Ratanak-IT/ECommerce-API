package org.example.datajpa.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;

    @Column(nullable = false)
    private String customerId;
    @Column(nullable = false )
    private String address;
    @Column(nullable = false)
    private Float discount;
    private String remark;
    @Column(nullable = false)
    private Boolean status;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private Boolean isDelete;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;

    @OneToMany(mappedBy = "order")
    private List<Payment> payment;

}
