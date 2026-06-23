package org.example.datajpa.features.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.datajpa.specification.dto.RequestDto;
import org.example.datajpa.features.category.dto.CategoryRequest;
import org.example.datajpa.features.category.dto.CategoryResponse;
import org.example.datajpa.specification.FilterSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    private final FilterSpecification<Category> filterSpecification;


    @Override
    public Page<CategoryResponse> searchByCriteria(RequestDto requestDto, Pageable pageable) {
        Specification<Category> categorySpecification = filterSpecification.getSearchSpecification(requestDto.getSearchRequestDtoList(), requestDto.getGlobalOperation());
        return categoryRepository.findAll(categorySpecification, pageable)
                .map(categoryMapper::toCategoryResponse);
    }


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

        if (id == null || id <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid category id"
            );
        }

        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        // can't update if category delete true
        if (Boolean.TRUE.equals(category.getIsDelete())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cannot update category"
            );
        }

        if (categoryRepository.existsByName(categoryRequest.name())
                && !category.getName().equals(categoryRequest.name())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category name already exists"
            );
        }

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
        List<Category> subCategories = categoryRepository.findAllByParentCategoryId(id);
        subCategories.forEach(subCategory -> subCategory.setIsDelete(true));

        categoryRepository.save(category);
        categoryRepository.saveAll(subCategories);
    }

    @Override
    public void hardDeleteCategory(Integer id) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category not found"));

        List<Category> subCategories= categoryRepository.findAllByParentCategoryId(id);
        categoryRepository.deleteAll(subCategories);
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
