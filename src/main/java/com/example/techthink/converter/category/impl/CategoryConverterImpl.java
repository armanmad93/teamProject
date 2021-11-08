package com.example.techthink.converter.category.impl;

import com.example.techthink.annotation.Convert;
import com.example.techthink.controller.category.model.response.CategoryResponse;
import com.example.techthink.converter.category.CategoryConverter;
import com.example.techthink.persistence.entity.category.Category;

import java.util.List;
import java.util.stream.Collectors;

@Convert
public class CategoryConverterImpl implements CategoryConverter {

    @Override
    public CategoryResponse fromCategoryToResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }
    @Override
    public List<CategoryResponse> fromCategoryToResponseList(List<Category> categories) {
        return categories.stream()
                .map(each -> fromCategoryToResponse(each))
                .collect(Collectors.toList());
    }
}
