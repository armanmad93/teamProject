package com.example.techthink.facade.course.impl;


import com.example.techthink.annotation.Facade;
import com.example.techthink.controller.course.model.request.CourseRequest;
import com.example.techthink.controller.course.model.response.CourseResponse;
import com.example.techthink.converter.course.CourseConverter;
import com.example.techthink.facade.course.CourseFacade;
import com.example.techthink.persistence.entity.course.Course;
import com.example.techthink.service.model.dto.course.CourseDTO;
import com.example.techthink.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Facade
public class CourseFacadeImpl implements CourseFacade {

    private final CourseService courseService;
    private final CourseConverter converter;

    @Autowired
    public CourseFacadeImpl(CourseService courseService, CourseConverter converter) {
        this.courseService = courseService;
        this.converter = converter;
    }

    @Override
    public CourseResponse create(CourseRequest request) {
        CourseDTO courseDTO = convertToDTO(request);
        Course course = courseService.create(courseDTO);
        CourseResponse courseResponse = converter.fromCourseToResponse(course, courseService.findSubCategories(course.getId()));
        return courseResponse;
    }

    @Override
    public CourseResponse readById(Integer id) {
        Course course = courseService.readById(id);
        CourseResponse courseResponse = converter.fromCourseToResponse(course, courseService.findSubCategories(course.getId()));
        return courseResponse;
    }

    @Override
    public List<CourseResponse> readAll() {
        List<Course> courses = courseService.readAll();
        return courses.stream()
                .map(each -> readById(each.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponse update(Integer id, CourseRequest request) {
        CourseDTO courseDTO = convertToDTO(request);
        Course updatedCourse = courseService.update(id, courseDTO);
        CourseResponse courseResponse =
                converter.fromCourseToResponse(updatedCourse
                        , courseService.findSubCategories(updatedCourse.getId()));
        return courseResponse;
    }

    @Override
    public Boolean delete(Integer id) {
        return courseService.delete(id);
    }

    private CourseDTO convertToDTO(CourseRequest request) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName(request.getName());
        courseDTO.setPrice(request.getPrice());
        courseDTO.setSubCategoryIds(request.getSubCategoryIds());
        return courseDTO;
    }

}
