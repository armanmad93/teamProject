package com.example.techthink.service.courseSection.impl;

import com.example.techthink.persistence.entity.courseSection.CourseSection;
import com.example.techthink.persistence.entity.user.User;
import com.example.techthink.persistence.entity.userSection.User_Section;
import com.example.techthink.persistence.repository.courseSection.CourseSectionRepository;
import com.example.techthink.persistence.repository.userSection.UserSectionRepository;
import com.example.techthink.service.address.AddressService;
import com.example.techthink.service.course.CourseService;
import com.example.techthink.service.courseSection.CourseSectionService;
import com.example.techthink.service.format.FormatService;
import com.example.techthink.service.model.dto.courseSection.CourseSectionDTO;
import com.example.techthink.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseSectionServiceImpl implements CourseSectionService {

    private final AddressService addressService;
    private final FormatService formatService;
    private final CourseService courseService;
    private final UserService userService;
    private final UserSectionRepository userSectionRepository;
    private final CourseSectionRepository sectionRepository;

    @Autowired
    public CourseSectionServiceImpl(AddressService addressService,
                                    FormatService formatService,
                                    CourseService courseService,
                                    UserService userService,
                                    UserSectionRepository userSectionRepository,
                                    CourseSectionRepository sectionRepository) {
        this.addressService = addressService;
        this.formatService = formatService;
        this.courseService = courseService;
        this.userService = userService;
        this.userSectionRepository = userSectionRepository;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public List<CourseSection> search(String term) {
        return sectionRepository.searchSection(term);
    }

    @Override
    public CourseSection create(CourseSectionDTO request) {
        User professor = userService.readById(request.getUserId());
        CourseSection section = buildSection(request);
        CourseSection savedSection = sectionRepository.save(section);
        connectUserAndSection(professor, savedSection);
        return savedSection;
    }

    @Override
    public CourseSection enrollIn(Long studentId, Long sectionId) {
        User student = userService.readById(studentId);
        CourseSection section = readById(sectionId);
        connectUserAndSection(student, section);
        return section;
    }

    @Override
    public CourseSection readById(Long id) {
        return sectionRepository.getById(id);
    }

    @Override
    public List<CourseSection> readAll() {
        return sectionRepository.findAll();
    }

    @Override
    public CourseSection update(Long id, CourseSectionDTO request) {
        CourseSection section = buildSection(sectionRepository.getById(id), request);
        return sectionRepository.save(section);
    }

    @Override
    public Boolean delete(Long id) {

        //first delete the relationship between given section and users;
        List<Long> allUserSectionGivenSection = userSectionRepository.getAllUserSectionGivenSection(id);
        allUserSectionGivenSection.forEach(each -> userSectionRepository.deleteById(each));

        sectionRepository.deleteById(id);
        return !sectionRepository.existsById(id);
    }

    @Override
    public CourseSection uploadPicture(Long id, String profilePicURL) {
        CourseSection sectionById = sectionRepository.getById(id);
        sectionById.setPhotoURL(profilePicURL);
        return sectionRepository.save(sectionById);
    }

    @Override
    public List<User> findAllUsersGivenSection(Long sectionId) {
        return userSectionRepository.findAllUsersGivenSection(sectionId);
    }

    @Override
    public Boolean deleteStudentFromSection(Long studentId, Long sectionId) {
        Long relationshipId = userSectionRepository.relationshipIdGivenStudentAndSection(studentId, sectionId);
        userSectionRepository.deleteById(relationshipId);
        return !userSectionRepository.existsById(relationshipId);

    }

    private void connectUserAndSection(User user, CourseSection section) {
        User_Section user_section = new User_Section();
        user_section.setCourseSection(section);
        user_section.setUser(user);
        userSectionRepository.save(user_section);
    }

    private CourseSection buildSection(CourseSectionDTO request) {
        return buildSection(new CourseSection(), request);
    }

    private CourseSection buildSection(CourseSection courseSection, CourseSectionDTO request) {
        courseSection.setName(request.getName());
        courseSection.setDescription(request.getDescription());
        courseSection.setStart_date(request.getStartDate());
        courseSection.setEnd_date(request.getEndDate());
        courseSection.setCapacity(request.getCapacity());
        courseSection.setPhotoURL(request.getPhotoURL());
        courseSection.setCourse(courseService.readById(request.getCourseId()));
        courseSection.setFormat(formatService.getFormatById(request.getFormatId()));
        courseSection.setAddress(addressService.readById(request.getAddressId()));
        return courseSection;
    }
}
