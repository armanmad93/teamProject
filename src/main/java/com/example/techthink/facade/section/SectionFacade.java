package com.example.techthink.facade.section;

import com.example.techthink.controller.course.model.request.CourseSectionRequest;
import com.example.techthink.controller.course.model.response.CourseSectionResponse;
import com.example.techthink.controller.user.model.response.SectionParticipantsResponse;

import java.util.List;

public interface SectionFacade {
    CourseSectionResponse createSection(Long professorId, CourseSectionRequest request);

    SectionParticipantsResponse enrollIn(Long studentId, Long sectionId);

    CourseSectionResponse readById(Long id);

    List<CourseSectionResponse> readAll();

    CourseSectionResponse update(Long id, CourseSectionRequest request);

    Boolean delete(Long id);

    Boolean deleteStudentFromSection(Long studentId, Long sectionId);

    CourseSectionResponse setSectionPic(Long sectionId, String picURL);

    List<CourseSectionResponse> search(String term);
}
