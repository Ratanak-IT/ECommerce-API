package org.example.datajpa.mapping;


import org.example.datajpa.domain.Category;
import org.example.datajpa.dto.CategoryRequest;
import org.example.datajpa.dto.CategoryResponse;
import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponse toCategoryResponse(Category category);
    Category toCategory(CategoryRequest categoryRequest);

}
