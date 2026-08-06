package com.project.helpdesk.infrastructure.DTOs.user;

import com.project.helpdesk.domain.entities.User;

public class UserDTOMapper {
    public UserResponse toResponse(User user) {
        return new UserResponse(user.name(), user.role());
    }

    public User toUser(UserRequest request) {
        return new User(request.name(), request.password(), request.role());
    }
}
