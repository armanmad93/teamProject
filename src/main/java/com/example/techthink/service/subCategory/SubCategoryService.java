package com.example.techthink.service.subCategory;

import com.example.techthink.persistence.entity.subCategory.SubCategory;
import com.example.techthink.service.model.dto.subCategory.SubCategoryDTO;

import java.util.List;

public interface SubCategoryService {
    SubCategory create(SubCategoryDTO request);
    SubCategory readById(Integer id);
    List<SubCategory> readAll();
    SubCategory update(Integer id, SubCategoryDTO request);
    Boolean delete(Integer id);
}
