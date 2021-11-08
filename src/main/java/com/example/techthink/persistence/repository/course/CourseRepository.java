package com.example.techthink.persistence.repository.course;

import com.example.techthink.persistence.entity.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
