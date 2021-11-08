package com.example.techthink.facade.section.impl;

import com.example.techthink.annotation.Facade;
import com.example.techthink.controller.course.model.request.CourseSectionRequest;
import com.example.techthink.controller.course.model.response.CourseSectionResponse;
import com.example.techthink.controller.user.model.response.SectionParticipantsResponse;
import com.example.techthink.converter.section.SectionConverter;
import com.example.techthink.facade.section.SectionFacade;
import com.example.techthink.persistence.entity.courseSection.CourseSection;
import com.example.techthink.persistence.entity.user.User;
import com.example.techthink.service.assignment.AssignmentService;
import com.example.techthink.service.model.dto.courseSection.CourseSectionDTO;
import com.example.techthink.service.courseSection.CourseSectionService;
import com.example.techthink.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Facade
public class SectionFacadeImpl implements SectionFacade {

    private final CourseSectionService sectionService;
    private final SectionConverter converter;
    private final CourseService courseService;
    private final AssignmentService assignmentService;

    @Autowired
    public SectionFacadeImpl(CourseSectionService sectionService, SectionConverter converter, CourseService courseService, AssignmentService assignmentService) {
        this.sectionService = sectionService;
        this.converter = converter;
        this.courseService = courseService;
        this.assignmentService = assignmentService;
    }

    @Override
    public CourseSectionResponse createSection(Long professorId, CourseSectionRequest request) {
        CourseSectionDTO sectionDTO = convertToDTO(professorId, request);
        CourseSection courseSection = sectionService.create(sectionDTO);
        CourseSectionResponse courseSectionResponse =
                converter.fromSectionToResponse(courseSection,
                        courseService.findSubCategories(courseSection.getCourse().getId()));
        return courseSectionResponse;
    }

    @Override
    public SectionParticipantsResponse enrollIn(Long studentId, Long sectionId) {
        CourseSection section = sectionService.enrollIn(studentId, sectionId);
        List<User> allUsersGivenSection = sectionService.findAllUsersGivenSection(sectionId);
        SectionParticipantsResponse sectionParticipantsResponse =
                converter.fromSectionToResponseWithParticipants(section,
                        allUsersGivenSection,
                        courseService.findSubCategories(section.getCourse().getId()));
        return sectionParticipantsResponse;
    }

    @Override
    public CourseSectionResponse readById(Long id) {
        CourseSection section = sectionService.readById(id);
        CourseSectionResponse courseSectionResponse = converter.fromSectionToResponse(section,
                courseService.findSubCategories(section.getCourse().getId()));
        return courseSectionResponse;
    }

    @Override
    public List<CourseSectionResponse> readAll() {
        List<CourseSection> courseSections = sectionService.readAll();
        return courseSections.stream()
                .map(each -> readById(each.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public CourseSectionResponse update(Long id, CourseSectionRequest request) {
        CourseSectionDTO courseSectionDTO = convertToDTOWithoutUpdatingProfessor(request);
        CourseSection update = sectionService.update(id, courseSectionDTO);
        CourseSectionResponse courseSectionResponse = converter.fromSectionToResponse(update,
                courseService.findSubCategories(update.getCourse().getId()));
        return courseSectionResponse;
    }

    @Override
    public Boolean delete(Long id) {
        //delete all assignments of the given section before deleting section
        List<Long> allAssignmentGivenSection = assignmentService.assignmentIdGivenSectionId(id);
        allAssignmentGivenSection.forEach(each -> assignmentService.delete(each));
        return sectionService.delete(id);

    }

    @Override
    public Boolean deleteStudentFromSection(Long studentId, Long sectionId) {
        return sectionService.deleteStudentFromSection(studentId, sectionId);
    }

    @Override
    public CourseSectionResponse setSectionPic(Long sectionId, String picURL) {
        CourseSection section = sectionService.uploadPicture(sectionId, picURL);
        CourseSectionResponse courseSectionResponse = converter.fromSectionToResponse(section,
                courseService.findSubCategories(section.getCourse().getId()));
        return courseSectionResponse;
    }

    @Override
    public List<CourseSectionResponse> search(String term) {
        List<CourseSection> searched = sectionService.search(term);
        List<CourseSectionResponse> searchedList = searched
                .stream()
                .map(each -> converter.fromSectionToResponse(each,
                        courseService.findSubCategories(each.getCourse().getId())))
                .collect(Collectors.toList());
        return searchedList;
    }

    private CourseSectionDTO convertToDTO(Long professorId, CourseSectionRequest request) {
        CourseSectionDTO courseSectionDTO = convertToDTOWithoutUpdatingProfessor(request);
        courseSectionDTO.setUserId(professorId);
        return courseSectionDTO;
    }

    private CourseSectionDTO convertToDTOWithoutUpdatingProfessor(CourseSectionRequest request) {
        CourseSectionDTO courseSectionDTO = new CourseSectionDTO();
        courseSectionDTO.setName(request.getName());
        courseSectionDTO.setDescription(request.getDescription());
        courseSectionDTO.setStartDate(request.getStartDate());
        courseSectionDTO.setEndDate(request.getEndDate());
        courseSectionDTO.setCapacity(request.getCapacity());
        courseSectionDTO.setCourseId(request.getCourseId());
        courseSectionDTO.setFormatId(request.getFormatId());
        courseSectionDTO.setAddressId(request.getAddressId());
        return courseSectionDTO;
    }
}
