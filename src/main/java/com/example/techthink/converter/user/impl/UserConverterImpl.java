package com.example.techthink.converter.user.impl;

import com.example.techthink.annotation.Convert;
import com.example.techthink.controller.user.model.response.UserResponse;
import com.example.techthink.converter.user.UserConverter;
import com.example.techthink.persistence.entity.user.User;

import java.util.stream.Collectors;

@Convert
public class UserConverterImpl implements UserConverter {

    @Override
    public UserResponse fromUserToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirst_name(user.getFirst_name());
        userResponse.setLast_name(user.getLast_name());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setDescription(user.getDescription());
        userResponse.setProfilePictureURL(user.getProfilePictureURL());
        userResponse.setRoleName(user.getRoles().stream()
                .map(each -> each.getName().toString())
                .collect(Collectors.toList()));

        return userResponse;
    }
}
