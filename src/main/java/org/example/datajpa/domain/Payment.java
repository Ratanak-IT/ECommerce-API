package org.example.datajpa.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Positive
    private Double amount;
    @Column(length = 50)
    private String paymentMethod;
    @Column(length = 50, nullable = false)
    private String paymentStatus;
    @Column(length = 50, unique = true, nullable = false)
    private String transactionId;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @ManyToOne
    private Order order;
}
