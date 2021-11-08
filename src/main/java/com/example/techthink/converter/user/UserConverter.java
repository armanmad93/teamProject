package com.example.techthink.converter.user;

import com.example.techthink.controller.user.model.response.UserResponse;
import com.example.techthink.persistence.entity.user.User;

public interface UserConverter {
    UserResponse fromUserToResponse(User user);
}
