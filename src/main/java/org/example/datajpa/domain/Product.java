package org.example.datajpa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


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
  private Boolean isDelete;

  @ManyToOne
  private Category category;

  @OneToMany(mappedBy = "product")
  private List<OrderLine> orderLines;
}
