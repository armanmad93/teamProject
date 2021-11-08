package com.example.techthink.converter.subCategory.impl;

import com.example.techthink.annotation.Convert;
import com.example.techthink.controller.category.model.response.CategoryResponse;
import com.example.techthink.controller.category.model.response.SubCategoryResponse;
import com.example.techthink.converter.category.CategoryConverter;
import com.example.techthink.converter.subCategory.SubCategoryConverter;
import com.example.techthink.persistence.entity.subCategory.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Convert
public class SubCategoryConverterImpl implements SubCategoryConverter {

    private final CategoryConverter categoryConverterImpl;

    @Autowired
    public SubCategoryConverterImpl(CategoryConverter categoryConverterImpl) {
        this.categoryConverterImpl = categoryConverterImpl;
    }

    @Override
    public SubCategoryResponse fromSubCategoryToResponse(SubCategory subCategory) {
        SubCategoryResponse subCategoryResponse = new SubCategoryResponse();
        subCategoryResponse.setId(subCategory.getId());
        subCategoryResponse.setName(subCategory.getName());
        CategoryResponse categoryResponse = categoryConverterImpl.fromCategoryToResponse(subCategory.getCategory());
        subCategoryResponse.setCategory(categoryResponse);
        return subCategoryResponse;
    }

    @Override
    public List<SubCategoryResponse> fromSubCategoryToResponseList(List<SubCategory> subCategories) {
        return subCategories.stream()
                .map(each -> fromSubCategoryToResponse(each))
                .collect(Collectors.toList());
    }
}
