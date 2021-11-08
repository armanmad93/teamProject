package com.example.techthink.converter;

import com.example.techthink.persistence.entity.role.Role;
import com.example.techthink.persistence.RoleName;
import com.example.techthink.persistence.entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class ConverterTest {

//    private Converter converter;


    @BeforeEach
    void setUp() {

    }

    @Test
    void fromUserToResponse() {

//        List<Role> roles = new ArrayList<>();
//        Role role = new Role();
//        role.setName(RoleName.STUDENT);
//        roles.add(role);
//
//        List<String> list = roles
//                .stream()
//                .map(each -> each.getName().toString())
//                .collect(Collectors.toList());
//
//        user.setId(1L);
//        user.setUsername("Arman");
//        user.setDescription("test");
//        user.setEmail("araman@gmail.com");
//        user.setRoles(roles);
//        user.setFirst_name("Arman");
//        user.setLast_name("Karapetyan");
//        user.setProfilePictureURL("https://teckthink.s3.eu-central-1.amazonaws.com/artwork-vasily-kandinsky-composition-8-37.262.jpg");
//
//
//        List<String> rolesString = new ArrayList<>();
//        rolesString.add("STUDENT");
//        userResponse.setId(1L);
//        userResponse.setUsername("Arman");
//        userResponse.setDescription("test");
//        userResponse.setEmail("araman@gmail.com");
//        userResponse.setRoleName(rolesString);
//        userResponse.setFirst_name("Arman");
//        userResponse.setLast_name("Karapetyan");
//        userResponse.setProfilePictureURL("https://teckthink.s3.eu-central-1.amazonaws.com/artwork-vasily-kandinsky-composition-8-37.262.jpg");
//
//        Assertions.assertEquals(user.getId(), userResponse.getId());
//        Assertions.assertEquals(user.getUsername(), userResponse.getUsername());
//        Assertions.assertEquals(user.getFirst_name(), userResponse.getFirst_name());
//        Assertions.assertEquals(user.getLast_name(), userResponse.getLast_name());
//        Assertions.assertEquals(user.getEmail(), userResponse.getEmail());
//        Assertions.assertEquals(user.getDescription(), userResponse.getDescription());
//        Assertions.assertEquals(user.getProfilePictureURL(), userResponse.getProfilePictureURL());
//        Assertions.assertEquals(list, userResponse.getRoleName());
    }


    @Test
    void someTest() {
        User user = new User();
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setName(RoleName.STUDENT);
        roles.add(role);
        List<String> list = roles
                .stream()
                .map(each -> each.getName().toString())
                .collect(Collectors.toList());
        user.setId(1L);
        user.setUsername("Arman");
        user.setDescription("test");
        user.setEmail("araman@gmail.com");
        user.setRoles(roles);
        user.setFirst_name("Arman");
        user.setLast_name("Karapetyan");
        user.setProfilePictureURL("https://teckthink.s3.eu-central-1.amazonaws.com/artwork-vasily-kandinsky-composition-8-37.262.jpg");

//        UserResponse result = converter.fromUserToResponse(user);

//        UserTestUtil.assertUser(user, result);
    }



    @Test
    void fromSectionToResponse() {

    }

    @Test
    void fromSectionToResponseWithParticipants() {
    }

    @Test
    void fromAddressToResponse() {
    }

    @Test
    void fromAddressToResponseList() {
    }

    @Test
    void fromCategoryToResponse() {
    }

    @Test
    void fromCategoryToResponseList() {
    }

    @Test
    void fromSubCategoryToResponse() {
    }

    @Test
    void fromSubCategoryToResponseList() {
    }

    @Test
    void fromAssignmentToResponse() {
    }

    @Test
    void fromCourseToResponse() {
    }

    @Test
    void fromCourseToResponseGivenSubCategories() {
    }
}