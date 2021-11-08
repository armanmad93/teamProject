package com.example.techthink.service.category.impl;

import com.example.techthink.persistence.entity.category.Category;
import com.example.techthink.persistence.repository.category.CategoryRepository;
import com.example.techthink.service.category.CategoryService;
import com.example.techthink.service.model.dto.category.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(CategoryDTO request) {
        return categoryRepository.save(build(request));
    }

    @Override
    public Category readById(Integer id) {
        return categoryRepository.getById(id);
    }

    @Override
    public List<Category> readAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category update(Integer id, CategoryDTO request) {
        Category category = categoryRepository.getById(id);
        return categoryRepository.save(build(category, request));
    }

    @Override
    public Boolean delete(Integer id) {
        categoryRepository.deleteById(id);
        return !categoryRepository.existsById(id);
    }

    private Category build(CategoryDTO request) {
        return build(new Category() , request);
    }

    private Category build(Category category, CategoryDTO request) {
        category.setName(request.getName());
        return category;
    }
}
