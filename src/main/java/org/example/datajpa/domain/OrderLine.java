package org.example.datajpa.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orderline")
public class OrderLine {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private Integer qty;
    private Double unitPrice;
//    private Double discount;
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
