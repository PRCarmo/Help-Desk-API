package com.project.helpdesk.infrastructure.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

import com.project.helpdesk.application.useCases.*;
import com.project.helpdesk.infrastructure.controllers.DTOs.CreateUserRequest;
import com.project.helpdesk.infrastructure.controllers.DTOs.CreateUserResponse;
import com.project.helpdesk.infrastructure.controllers.DTOs.UserDTOMapper;
import com.project.helpdesk.domain.entities.User;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserInteractor createUserInteractor;
    private final DeleteUserInteractor deleteUserInteractor;
    private final GetUserByIdInteractor getUserByIdInteractor;
    private final ListAllUsersInteractor listAllUsersInteractor;
    private final UserDTOMapper userDTOMapper;
    
    @PostMapping
    CreateUserResponse create(@RequestBody CreateUserRequest request) {
        User UserBussinessObj = userDTOMapper.toUser(request);
        User user = createUserInteractor.createUser(UserBussinessObj);
        return userDTOMapper.toResponse(user);
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
            getUserByIdInteractor
                .getUserById(id)
            );
    }

    @GetMapping
    ResponseEntity<List<User>> listAll() {
        return ResponseEntity.ok(
            listAllUsersInteractor
                .listAllUsers()
            );
    }
    
    // TODO: Corrigir problemas de vazamento das entidades ex: métodos get em ambos os controllers
    //TODO: Transformar essa list em Page futuramente
    
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {

        deleteUserInteractor.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    // TODO: refatorar código que fere SRP (ex: PostMapping)
    // TODO: avaliar o uso de uma response entity nos métodos HTTP e garantir que sigam o mesmo padrão
    // 
    


}
