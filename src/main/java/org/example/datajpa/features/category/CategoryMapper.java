package org.example.datajpa.features.category;


import org.example.datajpa.features.category.dto.CategoryRequest;
import org.example.datajpa.features.category.dto.CategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponse toCategoryResponse(Category category);
    Category toCategory(CategoryRequest categoryRequest);

}
