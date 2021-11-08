package com.example.techthink.service.category;

import com.example.techthink.persistence.entity.category.Category;
import com.example.techthink.service.model.dto.category.CategoryDTO;

import java.util.List;

public interface CategoryService {

    Category create(CategoryDTO request);
    Category readById(Integer id);
    List<Category> readAll();
    Category update(Integer id, CategoryDTO request);
    Boolean delete(Integer id);

}
