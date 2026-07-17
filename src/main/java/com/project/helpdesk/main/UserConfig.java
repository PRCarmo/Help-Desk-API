package com.project.helpdesk.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.helpdesk.application.gateways.UserGateway;
import com.project.helpdesk.application.useCases.*;
import com.project.helpdesk.infrastructure.gateways.UserEntityMapper;
import com.project.helpdesk.infrastructure.persistence.UserRepository;
import com.project.helpdesk.infrastructure.gateways.UserRepositoryGateway;
import com.project.helpdesk.infrastructure.controllers.DTOs.UserDTOMapper;

@Configuration
public class UserConfig {
    @Bean
    CreateUserInteractor createUserCase(UserGateway userGateway) {
        return new CreateUserInteractor(userGateway);
    }

    @Bean
    DeleteUserInteractor deleteUserCase(UserGateway userGateway) {
        return new DeleteUserInteractor(userGateway);
    }

    @Bean
    GetUserByIdInteractor getUserByIdInteractor(UserGateway userGateway) {
        return new GetUserByIdInteractor(userGateway);
    }

    @Bean
    UserGateway userGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        return new UserRepositoryGateway(userRepository, userEntityMapper);
    }

    @Bean
    UserEntityMapper userEntityMapper() {
        return new UserEntityMapper();
    }

    @Bean
    UserDTOMapper userDTOMapper() {
        return new UserDTOMapper();
    }
}
