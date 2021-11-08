package com.example.techthink.facade.course;

import com.example.techthink.controller.course.model.request.CourseRequest;
import com.example.techthink.controller.course.model.response.CourseResponse;

import java.util.List;

public interface CourseFacade {
    CourseResponse create(CourseRequest request);

    CourseResponse readById(Integer id);

    List<CourseResponse> readAll();

    CourseResponse update(Integer id, CourseRequest request);

    Boolean delete(Integer id);
}
