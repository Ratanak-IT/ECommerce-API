package org.example.datajpa.features.product;

import org.example.datajpa.features.product.dto.ProductRequest;
import org.example.datajpa.features.product.dto.ProductResponse;
import org.example.datajpa.features.product.dto.UpdateProductRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toProductResponse(Product product);
    Product toProduct(ProductRequest productRequest);
    Product toUpdateRequestToProduct(UpdateProductRequest productRequest);
}
