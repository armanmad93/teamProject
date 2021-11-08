package com.example.techthink.converter.section;

import com.example.techthink.controller.course.model.response.CourseSectionResponse;
import com.example.techthink.controller.user.model.response.SectionParticipantsResponse;
import com.example.techthink.persistence.entity.courseSection.CourseSection;
import com.example.techthink.persistence.entity.subCategory.SubCategory;
import com.example.techthink.persistence.entity.user.User;

import java.util.List;

public interface SectionConverter {

    CourseSectionResponse fromSectionToResponse(CourseSection section, List<SubCategory> subCategories);

    SectionParticipantsResponse fromSectionToResponseWithParticipants(CourseSection section,
                                                                      List<User> list,
                                                                      List<SubCategory> subCategories);
}
