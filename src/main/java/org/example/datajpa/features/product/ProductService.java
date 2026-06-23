package org.example.datajpa.features.product;

import org.example.datajpa.features.product.dto.ProductRequest;
import org.example.datajpa.specification.dto.RequestDto;
import org.example.datajpa.features.product.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<ProductResponse> filterAll(RequestDto requestDto, Pageable pageable);
    ProductResponse createProduct(ProductRequest productRequest);
    Page<ProductResponse> getProducts(int page, int size);
    ProductResponse updateProduct(Integer id, ProductRequest productRequest);
    void deleteProduct(Integer id);
    void softDeleteProduct(Integer id);
}
