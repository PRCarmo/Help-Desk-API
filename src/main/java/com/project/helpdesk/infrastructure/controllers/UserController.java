package com.project.helpdesk.infrastructure.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

import com.project.helpdesk.application.useCases.*;
import com.project.helpdesk.infrastructure.controllers.DTOs.CreateUserRequest;
import com.project.helpdesk.infrastructure.controllers.DTOs.CreateUserResponse;
import com.project.helpdesk.infrastructure.controllers.DTOs.UserDTOMapper;
import com.project.helpdesk.domain.entities.User;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserInteractor createUserInteractor;
    private final DeleteUserInteractor deleteUserInteractor;
    private final UserDTOMapper userDTOMapper;
    
    @PostMapping
    CreateUserResponse create(@RequestBody CreateUserRequest request) {
        User UserBussinessObj = userDTOMapper.toUser(request);
        User user = createUserInteractor.createUser(UserBussinessObj);
        return userDTOMapper.toResponse(user);
    }
    
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {

        deleteUserInteractor.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    // TODO: refatorar código que fere SRP (ex: PostMapping)
    // TODO: avaliar o uso de uma response entity nos métodos HTTP e garantir que sigam o mesmo padrão
    // 
    


}
