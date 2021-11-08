package com.example.techthink.service.courseSection;

import com.example.techthink.persistence.entity.courseSection.CourseSection;
import com.example.techthink.persistence.entity.user.User;
import com.example.techthink.service.model.dto.courseSection.CourseSectionDTO;

import java.util.List;

public interface CourseSectionService {

    List<CourseSection> search(String term);

    CourseSection create(CourseSectionDTO request);

    CourseSection enrollIn(Long studentId, Long sectionId);

    CourseSection readById(Long id);

    List<CourseSection> readAll();

    CourseSection update(Long id, CourseSectionDTO request);

    Boolean delete(Long id);

    CourseSection uploadPicture(Long id, String profilePicURL);

    List<User> findAllUsersGivenSection(Long SectionId);

    Boolean deleteStudentFromSection(Long studentId, Long sectionId);

}
