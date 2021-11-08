package com.example.techthink.converter.course;

import com.example.techthink.controller.course.model.response.CourseResponse;
import com.example.techthink.persistence.entity.course.Course;
import com.example.techthink.persistence.entity.subCategory.SubCategory;

import java.util.List;

public interface CourseConverter {
    CourseResponse fromCourseToResponse(Course course, List<SubCategory> subCategories);
}
