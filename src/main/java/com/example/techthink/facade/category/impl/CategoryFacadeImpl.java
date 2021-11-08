package com.example.techthink.facade.category.impl;

import com.example.techthink.annotation.Facade;
import com.example.techthink.controller.category.model.request.CategoryRequest;
import com.example.techthink.controller.category.model.response.CategoryResponse;
import com.example.techthink.converter.category.CategoryConverter;
import com.example.techthink.facade.category.CategoryFacade;
import com.example.techthink.persistence.entity.category.Category;
import com.example.techthink.service.model.dto.category.CategoryDTO;
import com.example.techthink.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Facade
public class CategoryFacadeImpl implements CategoryFacade {

    private final CategoryService categoryService;
    private final CategoryConverter converter;

    @Autowired
    public CategoryFacadeImpl(CategoryService categoryService, CategoryConverter converter) {
        this.categoryService = categoryService;
        this.converter = converter;
    }

    @Override
    public CategoryResponse create(CategoryRequest request){
        CategoryDTO categoryDTO = convertToDTO(request);
        return converter.fromCategoryToResponse(categoryService.create(categoryDTO));
    }

    @Override
    public CategoryResponse readById(Integer id){
        Category category = categoryService.readById(id);
        return converter.fromCategoryToResponse(category);
    }

    @Override
    public List<CategoryResponse> readAll(){
        List<Category> categories = categoryService.readAll();
        return converter.fromCategoryToResponseList(categories);
    }

    @Override
    public CategoryResponse update(Integer id, CategoryRequest request){
        CategoryDTO categoryDTO = convertToDTO(request);
        return converter.fromCategoryToResponse(categoryService.update(id, categoryDTO));
    }

    @Override
    public Boolean delete(Integer id){
        return categoryService.delete(id);
    }

    private CategoryDTO convertToDTO(CategoryRequest request){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(request.getName());
        return categoryDTO;
    }
}
