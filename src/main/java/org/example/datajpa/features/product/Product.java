package org.example.datajpa.features.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.datajpa.features.category.Category;
import org.example.datajpa.features.orderLine.OrderLine;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;
import java.util.UUID;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(unique = true, nullable = false, length = 100)
  private String code;

  @Column(unique = true, nullable = false)
  private String slug;

  @Column(nullable = false)
  private String name;

  @Column(length = 500)
  private String description;

  @Column(nullable = false)
  private String thumbnail;

  @Column(nullable = false)
  private Double unitPrice;

  @Column(nullable = false)
  private Integer qty;
  @Column(nullable = false)
  private Boolean isAvailable;

  @Column(nullable = false)
  private Boolean isDelete = false;

  @ManyToOne
  private Category category;

  @OneToMany(mappedBy = "product")
  private List<OrderLine> orderLines;
}
