package com.example.techthink.facade.assignment;

import com.example.techthink.controller.assignment.model.AssignmentRequest;
import com.example.techthink.controller.assignment.model.AssignmentResponse;

import java.util.List;


public interface AssignmentFacade {
    AssignmentResponse create(AssignmentRequest request, String file);

    AssignmentResponse readById(Long id);

    List<AssignmentResponse> readAll();

    AssignmentResponse update(Long id, AssignmentRequest request, String file);

    Boolean delete(Long id);
}
