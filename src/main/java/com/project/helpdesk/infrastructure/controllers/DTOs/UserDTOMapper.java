package com.project.helpdesk.infrastructure.controllers.DTOs;

import com.project.helpdesk.domain.entities.User;

public class UserDTOMapper {
    public CreateUserResponse toResponse(User user) {
        return new CreateUserResponse(user.name(), user.role());
    }

    public User toUser(CreateUserRequest request) {
        return new User(request.name(), request.password(), request.role());
    }
}
