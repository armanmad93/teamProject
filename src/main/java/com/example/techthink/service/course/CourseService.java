package com.example.techthink.service.course;

import com.example.techthink.persistence.entity.course.Course;
import com.example.techthink.persistence.entity.subCategory.SubCategory;
import com.example.techthink.service.model.dto.course.CourseDTO;

import java.util.List;

public interface CourseService {
    Course create(CourseDTO request);
    Course readById(Integer id);
    List<Course> readAll();
    Course update(Integer id, CourseDTO request);
    Boolean delete(Integer id);
    List<SubCategory> findSubCategories(Integer courseId);

}