package org.example.datajpa.features.category;


import jakarta.persistence.*;
import lombok.*;
import org.example.datajpa.features.product.Product;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true, length = 50)
    private String name;
    private String description;
    private String icon;
    private Boolean isDelete;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;
}
