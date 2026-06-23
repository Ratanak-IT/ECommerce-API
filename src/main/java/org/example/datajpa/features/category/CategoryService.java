package org.example.datajpa.features.category;

import org.example.datajpa.specification.dto.RequestDto;
import org.example.datajpa.features.category.dto.CategoryRequest;
import org.example.datajpa.features.category.dto.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Page<CategoryResponse> searchByCriteria(RequestDto requestDto, Pageable pageable);


    CategoryResponse createCategory(CategoryRequest categoryRequest);

    Page<CategoryResponse> getAllCategories(int page, int size);

    CategoryResponse getCategoryById(Integer id);

    void softDeleteCategory(Integer id);
    void hardDeleteCategory(Integer id);

    CategoryResponse updateCategoryById(Integer id, CategoryRequest categoryRequest);
    Page<CategoryResponse> getSubCategoriesByMainId(Integer parentId, int page, int size);
}
