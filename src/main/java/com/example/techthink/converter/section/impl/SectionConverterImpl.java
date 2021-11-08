package com.example.techthink.converter.section.impl;

import com.example.techthink.annotation.Convert;
import com.example.techthink.controller.course.model.response.CourseSectionResponse;
import com.example.techthink.controller.user.model.response.SectionParticipantsResponse;
import com.example.techthink.controller.user.model.response.UserResponse;
import com.example.techthink.converter.address.AddressConverter;
import com.example.techthink.converter.course.CourseConverter;
import com.example.techthink.converter.section.SectionConverter;
import com.example.techthink.converter.user.impl.UserConverterImpl;
import com.example.techthink.persistence.entity.courseSection.CourseSection;
import com.example.techthink.persistence.entity.subCategory.SubCategory;
import com.example.techthink.persistence.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Convert
public class SectionConverterImpl implements SectionConverter {

    private final CourseConverter courseConverterImpl;
    private final AddressConverter addressConverterImpl;
    private final UserConverterImpl userConverterImpl;

    @Autowired
    public SectionConverterImpl(CourseConverter courseConverterImpl, AddressConverter addressConverterImpl, UserConverterImpl userConverterImpl) {
        this.courseConverterImpl = courseConverterImpl;
        this.addressConverterImpl = addressConverterImpl;
        this.userConverterImpl = userConverterImpl;
    }

    @Override
    public CourseSectionResponse fromSectionToResponse(CourseSection section, List<SubCategory> subCategories) {
        CourseSectionResponse courseSectionResponse = new CourseSectionResponse();
        courseSectionResponse.setId(section.getId());
        courseSectionResponse.setName(section.getName());
        courseSectionResponse.setDescription(section.getDescription());
        courseSectionResponse.setStartDate(section.getStart_date());
        courseSectionResponse.setEndDate(section.getEnd_date());
        courseSectionResponse.setCapacity(section.getCapacity());
        courseSectionResponse.setCourse(courseConverterImpl.fromCourseToResponse(section.getCourse(), subCategories));
        courseSectionResponse.setFormat(section.getFormat().getName().toString());
        courseSectionResponse.setAddress(addressConverterImpl.fromAddressToResponse(section.getAddress()));
        return courseSectionResponse;
    }

    @Override
    public SectionParticipantsResponse fromSectionToResponseWithParticipants(CourseSection section,
                                                                             List<User> list,
                                                                             List<SubCategory> subCategories) {
        SectionParticipantsResponse sectionParticipantsResponse = new SectionParticipantsResponse();
        CourseSectionResponse courseSectionResponse = fromSectionToResponse(section, subCategories);
        sectionParticipantsResponse.setSectionResponse(courseSectionResponse);

        List<UserResponse> participants = list.stream()
                .map(each -> userConverterImpl.fromUserToResponse(each)).collect(Collectors.toList());

        sectionParticipantsResponse.setParticipants(participants);
        return sectionParticipantsResponse;
    }
}
