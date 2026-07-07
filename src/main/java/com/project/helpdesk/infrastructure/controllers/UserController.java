package com.project.helpdesk.infrastructure.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;

import com.project.helpdesk.application.useCases.CreateUserInteractor;
import com.project.helpdesk.infrastructure.controllers.DTOs.CreateUserRequest;
import com.project.helpdesk.infrastructure.controllers.DTOs.CreateUserResponse;
import com.project.helpdesk.infrastructure.controllers.DTOs.UserDTOMapper;
import com.project.helpdesk.domain.entities.User;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserInteractor createUserInteractor;
    private final UserDTOMapper userDTOMapper;
    
    @PostMapping
    CreateUserResponse create(@RequestBody CreateUserRequest request) {
        User UserBussinessObj = userDTOMapper.toUser(request);
        User user = createUserInteractor.createUser(UserBussinessObj);
        return userDTOMapper.toResponse(user);
    }
    
}
