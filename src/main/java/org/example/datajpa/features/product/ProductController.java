package org.example.datajpa.features.product;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.datajpa.features.product.dto.ProductRequest;
import org.example.datajpa.features.product.dto.UpdateProductRequest;
import org.example.datajpa.specification.dto.RequestDto;
import org.example.datajpa.features.product.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/search")
    public Page<ProductResponse> searchByCriteria(RequestDto requestDto, Pageable pageable) {
        return productService.filterAll(requestDto, pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductResponse createProduct(@Valid @RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping
    public Page<ProductResponse> getProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "25") int size) {
        return productService.getProducts(page, size);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable Integer id, @Valid @RequestBody UpdateProductRequest productRequest) {
        return productService.updateProduct(id, productRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/delete/{id}")
    public void softDeleteProduct(@PathVariable Integer id) {
        productService.softDeleteProduct(id);
    }

}
