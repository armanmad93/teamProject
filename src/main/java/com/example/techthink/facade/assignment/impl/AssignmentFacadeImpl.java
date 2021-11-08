package com.example.techthink.facade.assignment.impl;

import com.example.techthink.annotation.Facade;
import com.example.techthink.controller.assignment.model.AssignmentRequest;
import com.example.techthink.controller.assignment.model.AssignmentResponse;
import com.example.techthink.converter.assignment.AssignmentConverter;
import com.example.techthink.facade.assignment.AssignmentFacade;
import com.example.techthink.persistence.entity.assignment.Assignment;
import com.example.techthink.persistence.entity.subCategory.SubCategory;
import com.example.techthink.service.model.dto.assignment.AssignmentDTO;
import com.example.techthink.service.assignment.AssignmentService;
import com.example.techthink.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Facade
public class AssignmentFacadeImpl implements AssignmentFacade {

    private final AssignmentService service;
    private final AssignmentConverter converter;
    private final CourseService courseService;

    @Autowired
    public AssignmentFacadeImpl(AssignmentService service, AssignmentConverter converter, CourseService courseService) {
        this.service = service;
        this.converter = converter;
        this.courseService = courseService;
    }

    @Override
    public AssignmentResponse create(AssignmentRequest request, String file) {
        AssignmentDTO assignmentDTO = convertToDTO(request, file);
        Assignment assignment = service.create(assignmentDTO);
        List<SubCategory> subCategories = courseService
                .findSubCategories(assignment
                        .getCourseSection()
                        .getCourse()
                        .getId());
        return converter.fromAssignmentToResponse(assignment, subCategories);
    }

    @Override
    public AssignmentResponse readById(Long id) {
        Assignment assignment = service.readById(id);
        List<SubCategory> subCategories = courseService
                .findSubCategories(assignment
                        .getCourseSection()
                        .getCourse()
                        .getId());
        return converter.fromAssignmentToResponse(assignment, subCategories);
    }

    @Override
    public List<AssignmentResponse> readAll() {
        List<Assignment> assignments = service.readAll();
        return assignments
                .stream()
                .map(each -> readById(each.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public AssignmentResponse update(Long id, AssignmentRequest request, String file) {
        AssignmentDTO assignmentDTO = convertToDTO(request, file);
        Assignment update = service.update(id, assignmentDTO);
        List<SubCategory> subCategories = courseService.findSubCategories(update.getCourseSection().getCourse().getId());
        return converter.fromAssignmentToResponse(update, subCategories);
    }

    @Override
    public Boolean delete(Long id) {
        return service.delete(id);
    }

    private AssignmentDTO convertToDTO(AssignmentRequest request, String file) {
        AssignmentDTO assignmentDTO = new AssignmentDTO();
        assignmentDTO.setName(request.getName());
        assignmentDTO.setText(request.getText());
        assignmentDTO.setDueDate(request.getDueDate());
        assignmentDTO.setAssignmentFileURL(file);
        assignmentDTO.setCourseSectionId(request.getSectionID());
        return assignmentDTO;
    }
}
