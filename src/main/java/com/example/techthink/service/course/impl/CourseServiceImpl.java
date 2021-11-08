package com.example.techthink.service.course.impl;


import com.example.techthink.persistence.entity.course.Course;
import com.example.techthink.persistence.entity.subCategory.SubCategory;
import com.example.techthink.persistence.entity.subCategoryCourse.SubCategory_Course;
import com.example.techthink.persistence.repository.course.CourseRepository;
import com.example.techthink.persistence.repository.subCategoryCourse.SubCategoryCourseRepository;
import com.example.techthink.service.course.CourseService;
import com.example.techthink.service.model.dto.course.CourseDTO;
import com.example.techthink.service.subCategory.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final SubCategoryService subCategoryService;
    private final SubCategoryCourseRepository subCategoryCourseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, SubCategoryService subCategoryService, SubCategoryCourseRepository subCategoryCourseRepository) {
        this.courseRepository = courseRepository;
        this.subCategoryService = subCategoryService;
        this.subCategoryCourseRepository = subCategoryCourseRepository;
    }

    @Override
    public Course create(CourseDTO request) {
        Course build = build(request);
        Course savedCourse = courseRepository.save(build);
        List<Integer> subCategoryIds = request.getSubCategoryIds();
        List<SubCategory> subCategories = subCategoryIds.stream()
                .map(each -> {
                    SubCategory subCategory = subCategoryService.readById(each);
                    return subCategory;
                }).collect(Collectors.toList());
        connectCourseWithSubCategory(savedCourse, subCategories);
        return savedCourse;
    }

    @Override
    public Course readById(Integer id) {
        return courseRepository.getById(id);
    }

    @Override
    public List<Course> readAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course update(Integer id, CourseDTO request) {
        Course updated = build(courseRepository.getById(id), request);
        return courseRepository.save(updated);
    }

    @Override
    public Boolean delete(Integer id) {
        //first delete the relationship, then the course
        List<Integer> allSubCategoryCourseGivenCourse = subCategoryCourseRepository.getAllSubCategoryCourseGivenCourse(id);
        allSubCategoryCourseGivenCourse.forEach(each -> subCategoryCourseRepository.deleteById(each));

        courseRepository.deleteById(id);
        return !courseRepository.existsById(id);
    }

    @Override
    public List<SubCategory> findSubCategories(Integer courseId) {
        return subCategoryCourseRepository.findSubCategories(courseId);
    }

    private void connectCourseWithSubCategory(Course course, List<SubCategory> subCategories) {
        subCategories.stream()
                .map(each -> {
                    SubCategory_Course subCategory_course = new SubCategory_Course();
                    subCategory_course.setCourse(course);
                    subCategory_course.setSubCategory(each);
                    SubCategory_Course saved = subCategoryCourseRepository.save(subCategory_course);
                    return saved;
                })
                .collect(Collectors.toList());
    }

    private Course build(CourseDTO request) {
        return build(new Course(), request);
    }

    private Course build(Course course, CourseDTO request) {
        course.setName(request.getName());
        course.setPrice(request.getPrice());
        List<SubCategory> subCategories = request.getSubCategoryIds().stream()
                .map(each -> subCategoryService.readById(each)).collect(Collectors.toList());
        return course;
    }
}
