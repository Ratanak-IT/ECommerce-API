package org.example.datajpa.service;

import org.example.datajpa.dto.CategoryRequest;
import org.example.datajpa.dto.CategoryResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest categoryRequest);

    Page<CategoryResponse> getAllCategories(int page, int size);

    CategoryResponse getCategoryById(Integer id);

    void softDeleteCategory(Integer id);
    void hardDeleteCategory(Integer id);

    CategoryResponse updateCategoryById(Integer id, CategoryRequest categoryRequest);
    Page<CategoryResponse> getSubCategoriesByMainId(Integer parentId, int page, int size);
}
