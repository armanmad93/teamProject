package com.example.techthink.facade.user.impl;

import com.example.techthink.annotation.Facade;
import com.example.techthink.controller.user.model.request.RegisterRequest;
import com.example.techthink.controller.user.model.response.UserResponse;
import com.example.techthink.converter.user.UserConverter;
import com.example.techthink.facade.user.UserFacade;
import com.example.techthink.persistence.entity.user.User;
import com.example.techthink.service.model.dto.user.UserDTO;
import com.example.techthink.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Facade
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final UserConverter converter;

    @Autowired
    public UserFacadeImpl(UserService userService, UserConverter converter) {
        this.userService = userService;
        this.converter = converter;
    }

    @Override
    public UserResponse registerStudent(RegisterRequest request) {
        UserDTO userDTO = convertToDTO(request);
        return converter.fromUserToResponse(userService.register(userDTO));
    }

    @Override
    public UserResponse addProfessor(RegisterRequest request) {
        UserDTO userDTO = convertToDTO(request);
        return converter.fromUserToResponse(userService.addProfessor(userDTO));
    }

    @Override
    public UserResponse editProfile(Long id, RegisterRequest request) {
        UserDTO userDTO = convertToDTO(request);
        return converter.fromUserToResponse(userService.update(id, userDTO));
    }

    @Override
    public UserResponse readById(Long id) {
        User user = userService.readById(id);
        return converter.fromUserToResponse(user);
    }

    @Override
    public List<UserResponse> readAll() {
        List<User> users = userService.readAll();
        return users.stream()
                .map(each -> readById(each.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        return userService.delete(id);
    }

    @Override
    public UserResponse uploadPicture(Long id, String pictureURL) {
        User user = userService.uploadPicture(id, pictureURL);
        return converter.fromUserToResponse(user);
    }

    private UserDTO convertToDTO(RegisterRequest request) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(request.getFirstName());
        userDTO.setLastName(request.getLastName());
        userDTO.setUserName(request.getUserName());
        userDTO.setEmail(request.getMail());
        userDTO.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        userDTO.setDescription(request.getDescription());
        return userDTO;
    }
}
