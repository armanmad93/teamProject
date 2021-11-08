package com.example.techthink.converter.assignment;

import com.example.techthink.controller.assignment.model.AssignmentResponse;
import com.example.techthink.persistence.entity.assignment.Assignment;
import com.example.techthink.persistence.entity.subCategory.SubCategory;

import java.util.List;

public interface AssignmentConverter {

    AssignmentResponse fromAssignmentToResponse(Assignment assignment, List<SubCategory> subCategories);
}
