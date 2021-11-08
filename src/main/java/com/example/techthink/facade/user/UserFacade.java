package com.example.techthink.facade.user;

import com.example.techthink.controller.user.model.request.RegisterRequest;
import com.example.techthink.controller.user.model.response.UserResponse;

import java.util.List;

public interface UserFacade {
    UserResponse registerStudent(RegisterRequest request);

    UserResponse addProfessor(RegisterRequest request);

    UserResponse editProfile(Long id, RegisterRequest request);

    UserResponse readById(Long id);

    List<UserResponse> readAll();

    Boolean delete(Long id);

    UserResponse uploadPicture(Long id, String pictureURL);
}
