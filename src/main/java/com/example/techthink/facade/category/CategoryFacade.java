package com.example.techthink.facade.category;

import com.example.techthink.controller.category.model.request.CategoryRequest;
import com.example.techthink.controller.category.model.response.CategoryResponse;

import java.util.List;

public interface CategoryFacade {
    CategoryResponse create(CategoryRequest request);

    CategoryResponse readById(Integer id);

    List<CategoryResponse> readAll();

    CategoryResponse update(Integer id, CategoryRequest request);

    Boolean delete(Integer id);
}
