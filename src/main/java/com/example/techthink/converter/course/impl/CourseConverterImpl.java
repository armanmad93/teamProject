package com.example.techthink.converter.course.impl;

import com.example.techthink.annotation.Convert;
import com.example.techthink.controller.category.model.response.SubCategoryResponse;
import com.example.techthink.controller.course.model.response.CourseResponse;
import com.example.techthink.converter.course.CourseConverter;
import com.example.techthink.converter.subCategory.SubCategoryConverter;
import com.example.techthink.persistence.entity.course.Course;
import com.example.techthink.persistence.entity.subCategory.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Convert
public class CourseConverterImpl implements CourseConverter {

    private final SubCategoryConverter subCategoryConverterImpl;

    @Autowired
    public CourseConverterImpl(SubCategoryConverter subCategoryConverterImpl) {
        this.subCategoryConverterImpl = subCategoryConverterImpl;
    }

    @Override
    public CourseResponse fromCourseToResponse(Course course, List<SubCategory> subCategories) {
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setName(course.getName());
        courseResponse.setPrice(course.getPrice());

        List<SubCategoryResponse> subCategoryResponses = subCategories.stream()
                .map(each -> subCategoryConverterImpl.fromSubCategoryToResponse(each))
                .collect(Collectors.toList());
        courseResponse.setSubCategoryResponses(subCategoryResponses);
        return courseResponse;
    }
}
