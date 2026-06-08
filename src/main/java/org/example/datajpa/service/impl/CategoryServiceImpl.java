package org.example.datajpa.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.datajpa.domain.Category;
import org.example.datajpa.dto.CategoryRequest;
import org.example.datajpa.dto.CategoryResponse;
import org.example.datajpa.mapping.CategoryMapper;
import org.example.datajpa.repository.CategoryRepository;
import org.example.datajpa.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {

        if (categoryRepository.existsByName(request.name())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category already exists"
            );
        }

        Category parentCategory = null;

        if (request.parentCategoryId() != null) {
            parentCategory = categoryRepository
                    .findById(request.parentCategoryId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Parent category not found"
                    ));
        }

        Category category = categoryMapper.toCategory(request);
        category.setParentCategory(parentCategory);
        category.setIsDelete(false);

        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public CategoryResponse updateCategoryById(Integer id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        category.setName(categoryRequest.name());
        category.setDescription(categoryRequest.description());
        category.setIcon(categoryRequest.icon());
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @Override
    public Page<CategoryResponse> getAllCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryRepository.findAll(pageable)
                .map(categoryMapper::toCategoryResponse);
    }


    @Override
    public CategoryResponse getCategoryById(Integer id) {
        Category getById= categoryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category not found"));
        return categoryMapper.toCategoryResponse(getById);
    }

    @Override
    public void softDeleteCategory(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        category.setIsDelete(true);
        categoryRepository.save(category);
    }

    @Override
    public void hardDeleteCategory(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public  Page<CategoryResponse> getSubCategoriesByMainId(Integer parentId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        categoryRepository.findById(parentId)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Parent category not found"
                        ));

        return categoryRepository.findAllByParentCategoryId(parentId, pageable)
                .map(categoryMapper::toCategoryResponse);
    }
}
