package org.example.datajpa.features.category;


import lombok.RequiredArgsConstructor;
import org.example.datajpa.specification.dto.RequestDto;
import org.example.datajpa.features.category.dto.CategoryRequest;
import org.example.datajpa.features.category.dto.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }

    @PostMapping("/search")
    public Page<CategoryResponse> searchByCriteria(@RequestBody RequestDto requestDto, Pageable pageable) {
        return categoryService.searchByCriteria(requestDto, pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<CategoryResponse> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size) {
        return categoryService.getAllCategories(page, size);
    }



    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/subcategories")
    public  Page<CategoryResponse> getSubCategoriesByMainId(
            @RequestParam Integer parentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ) {
        return categoryService.getSubCategoriesByMainId(parentId, page, size);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void softDeleteCategory(@PathVariable Integer id) {
        categoryService.softDeleteCategory(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void hardDeleteCategory(@PathVariable Integer id) {
        categoryService.hardDeleteCategory(id);
    }


    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public CategoryResponse updateCategoryById(@PathVariable Integer id, @RequestBody CategoryRequest categoryRequest) {
        return categoryService.updateCategoryById(id, categoryRequest);
    }

}
