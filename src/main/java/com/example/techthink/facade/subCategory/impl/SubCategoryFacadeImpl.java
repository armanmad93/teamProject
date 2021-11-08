package com.example.techthink.facade.subCategory.impl;

import com.example.techthink.annotation.Facade;
import com.example.techthink.controller.category.model.request.SubCategoryRequest;
import com.example.techthink.controller.category.model.response.SubCategoryResponse;
import com.example.techthink.converter.subCategory.SubCategoryConverter;
import com.example.techthink.facade.subCategory.SubCategoryFacade;
import com.example.techthink.persistence.entity.subCategory.SubCategory;
import com.example.techthink.service.model.dto.subCategory.SubCategoryDTO;
import com.example.techthink.service.subCategory.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Facade
public class SubCategoryFacadeImpl implements SubCategoryFacade {

    private final SubCategoryService service;
    private final SubCategoryConverter converter;

    @Autowired
    public SubCategoryFacadeImpl(SubCategoryService service, SubCategoryConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @Override
    public SubCategoryResponse create(SubCategoryRequest request) {
        SubCategoryDTO subCategoryDTO = convertToDTO(request);
        return converter.fromSubCategoryToResponse(service.create(subCategoryDTO));
    }

    @Override
    public SubCategoryResponse readById(Integer id) {
        SubCategory subCategory = service.readById(id);
        return converter.fromSubCategoryToResponse(subCategory);
    }

    @Override
    public List<SubCategoryResponse> readAll() {
        List<SubCategory> subCategories = service.readAll();
        return converter.fromSubCategoryToResponseList(subCategories);
    }

    @Override
    public SubCategoryResponse update(Integer id, SubCategoryRequest request) {
        SubCategoryDTO subCategoryDTO = convertToDTO(request);
        return converter.fromSubCategoryToResponse(service.update(id, subCategoryDTO));
    }

    @Override
    public Boolean delete(Integer id) {
        return service.delete(id);
    }

    private SubCategoryDTO convertToDTO(SubCategoryRequest request) {
        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
        subCategoryDTO.setName(request.getName());
        subCategoryDTO.setCategoryId(request.getCategoryId());
        return subCategoryDTO;
    }
}
