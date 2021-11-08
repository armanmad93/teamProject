package com.example.techthink.service.subCategory.impl;

import com.example.techthink.persistence.entity.category.Category;
import com.example.techthink.persistence.entity.subCategory.SubCategory;
import com.example.techthink.persistence.repository.subCategory.SubCategoryRepository;
import com.example.techthink.service.category.CategoryService;
import com.example.techthink.service.model.dto.subCategory.SubCategoryDTO;
import com.example.techthink.service.subCategory.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryService categoryService;

    @Autowired
    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository, CategoryService categoryService) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryService = categoryService;
    }

    @Override
    public SubCategory create(SubCategoryDTO request) {
        SubCategory build = build(request);
        SubCategory saved = subCategoryRepository.save(build);
        return saved;
    }

    @Override
    public SubCategory readById(Integer id) {
        SubCategory byId = subCategoryRepository.getById(id);
        return byId;
    }

    @Override
    public List<SubCategory> readAll() {
        List<SubCategory> all = subCategoryRepository.findAll();
        return all;

    }

    @Override
    public SubCategory update(Integer id, SubCategoryDTO request) {
        SubCategory byId = subCategoryRepository.getById(id);
        SubCategory subCategory = build(byId, request);
        SubCategory updated = subCategoryRepository.save(subCategory);
        return updated;
    }

    @Override
    public Boolean delete(Integer id) {
        subCategoryRepository.deleteById(id);
        return !subCategoryRepository.existsById(id);
    }

    private SubCategory build(SubCategoryDTO request) {
        return build(new SubCategory(), request);
    }

    private SubCategory build(SubCategory subCategory, SubCategoryDTO request) {
        subCategory.setName(request.getName());
        Category category = categoryService.readById(request.getCategoryId());
        subCategory.setCategory(category);
        return subCategory;
    }
}
