package com.example.techthink.service.assignment.impl;

import com.example.techthink.persistence.entity.assignment.Assignment;
import com.example.techthink.persistence.entity.courseSection.CourseSection;
import com.example.techthink.persistence.repository.assignment.AssignmentRepository;
import com.example.techthink.service.assignment.AssignmentService;
import com.example.techthink.service.courseSection.CourseSectionService;
import com.example.techthink.service.model.dto.assignment.AssignmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final CourseSectionService sectionService;

    @Autowired
    public AssignmentServiceImpl(AssignmentRepository assignmentRepository, CourseSectionService sectionService) {
        this.assignmentRepository = assignmentRepository;
        this.sectionService = sectionService;
    }

    @Override
    public Assignment create(AssignmentDTO request) {
        return assignmentRepository.save(build(request));
    }

    @Override
    public Assignment readById(Long id) {
        return assignmentRepository.getById(id);
    }

    @Override
    public List<Assignment> readAll() {
        return assignmentRepository.findAll();
    }

    @Override
    public Assignment update(Long id, AssignmentDTO request) {
        Assignment assignment = assignmentRepository.getById(id);
        return assignmentRepository.save(build(assignment, request));
    }

    @Override
    public Boolean delete(Long id) {
        assignmentRepository.deleteById(id);
        return !assignmentRepository.existsById(id);
    }

    @Override
    public List<Long> assignmentIdGivenSectionId(Long id) {
        return assignmentRepository.assignmentIdGivenSectionId(id);
    }

    private Assignment build(AssignmentDTO request) {
        return build(new Assignment(), request);
    }

    private Assignment build(Assignment assignment, AssignmentDTO request) {
        assignment.setName(request.getName());
        assignment.setText(request.getText());
        assignment.setDueDate(request.getDueDate());
        assignment.setAssignmentFileURL(request.getAssignmentFileURL());
        CourseSection courseSection = sectionService.readById(request.getCourseSectionId());
        assignment.setCourseSection(courseSection);
        return assignment;
    }
}
