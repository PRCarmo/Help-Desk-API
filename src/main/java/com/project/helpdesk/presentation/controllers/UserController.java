package com.project.helpdesk.presentation.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

import com.project.helpdesk.application.useCases.user.*;
import com.project.helpdesk.infrastructure.DTOs.user.*;
import com.project.helpdesk.domain.entities.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserInteractor createUserInteractor;
    private final DeleteUserInteractor deleteUserInteractor;
    private final GetUserByIdInteractor getUserByIdInteractor;
    private final ListAllUsersInteractor listAllUsersInteractor;
    private final UpdateUserInteractor updateUserInteractor;
    private final UserDTOMapper userDTOMapper;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserResponse create(@RequestBody UserRequest request) {
        User UserBussinessObj = userDTOMapper.toUser(request);
        User user = createUserInteractor.createUser(UserBussinessObj);
        return userDTOMapper.toResponse(user);
    }

    @PutMapping("/{id}")
    UserResponse update(@PathVariable Long id, @RequestBody UserRequest request) {
        
        User userBussinesObj = userDTOMapper.toUser(request);
        
        User user = updateUserInteractor.updateUser(id, userBussinesObj);

        return userDTOMapper.toResponse(user);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    UserResponse getById(@PathVariable Long id) {
            
        User user = getUserByIdInteractor.getUserById(id);

        return userDTOMapper.toResponse(user);
        
    }

    @GetMapping
    List<User> listAll() {

        return listAllUsersInteractor.listAllUsers();
        
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id) {

        deleteUserInteractor.deleteUser(id);

    }
    
}
