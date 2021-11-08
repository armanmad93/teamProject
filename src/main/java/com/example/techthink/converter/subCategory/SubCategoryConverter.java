package com.example.techthink.converter.subCategory;

import com.example.techthink.controller.category.model.response.SubCategoryResponse;
import com.example.techthink.persistence.entity.subCategory.SubCategory;

import java.util.List;

public interface SubCategoryConverter {
    SubCategoryResponse fromSubCategoryToResponse(SubCategory subCategory);

    List<SubCategoryResponse> fromSubCategoryToResponseList(List<SubCategory> subCategories);
}
