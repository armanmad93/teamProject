package com.example.techthink.service.assignment;

import com.example.techthink.persistence.entity.assignment.Assignment;
import com.example.techthink.service.model.dto.assignment.AssignmentDTO;

import java.util.List;

public interface AssignmentService {

    Assignment create(AssignmentDTO request);

    Assignment readById(Long id);

    List<Assignment> readAll();

    Assignment update(Long id, AssignmentDTO request);

    Boolean delete(Long id);

    List<Long> assignmentIdGivenSectionId(Long id);
}
