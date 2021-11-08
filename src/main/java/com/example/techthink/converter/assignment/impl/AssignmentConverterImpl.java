package com.example.techthink.converter.assignment.impl;

import com.example.techthink.annotation.Convert;
import com.example.techthink.controller.assignment.model.AssignmentResponse;
import com.example.techthink.controller.course.model.response.CourseSectionResponse;
import com.example.techthink.converter.assignment.AssignmentConverter;
import com.example.techthink.converter.section.impl.SectionConverterImpl;
import com.example.techthink.persistence.entity.assignment.Assignment;
import com.example.techthink.persistence.entity.subCategory.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Convert
public class AssignmentConverterImpl implements AssignmentConverter {

    private final SectionConverterImpl sectionConverterImpl;

    @Autowired
    public AssignmentConverterImpl(SectionConverterImpl sectionConverterImpl) {
        this.sectionConverterImpl = sectionConverterImpl;
    }

    @Override
    public AssignmentResponse fromAssignmentToResponse(Assignment assignment, List<SubCategory> subCategories) {
        AssignmentResponse assignmentResponse = new AssignmentResponse();
        assignmentResponse.setId(assignment.getId());
        assignmentResponse.setName(assignment.getName());
        assignmentResponse.setText(assignment.getText());
        assignmentResponse.setAssignmentFileURL(assignment.getAssignmentFileURL());
        assignmentResponse.setDueDate(assignment.getDueDate());
        CourseSectionResponse sectionResponse = sectionConverterImpl.fromSectionToResponse(assignment.getCourseSection() , subCategories);
        assignmentResponse.setSection(sectionResponse);
        return assignmentResponse;
    }
}
