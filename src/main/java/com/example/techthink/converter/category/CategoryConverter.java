package com.example.techthink.converter.category;

import com.example.techthink.controller.category.model.response.CategoryResponse;
import com.example.techthink.persistence.entity.category.Category;

import java.util.List;

public interface CategoryConverter {
    CategoryResponse fromCategoryToResponse(Category category);

    List<CategoryResponse> fromCategoryToResponseList(List<Category> categories);
}
