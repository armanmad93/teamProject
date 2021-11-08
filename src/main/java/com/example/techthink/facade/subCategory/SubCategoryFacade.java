package com.example.techthink.facade.subCategory;

import com.example.techthink.controller.category.model.request.SubCategoryRequest;
import com.example.techthink.controller.category.model.response.SubCategoryResponse;

import java.util.List;

public interface SubCategoryFacade {
    SubCategoryResponse create(SubCategoryRequest request);

    SubCategoryResponse readById(Integer id);

    List<SubCategoryResponse> readAll();

    SubCategoryResponse update(Integer id, SubCategoryRequest request);

    Boolean delete(Integer id);
}
