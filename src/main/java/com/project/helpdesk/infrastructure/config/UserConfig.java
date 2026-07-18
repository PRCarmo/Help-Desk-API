package com.project.helpdesk.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.helpdesk.application.gateways.UserGateway;
import com.project.helpdesk.application.useCases.user.*;
import com.project.helpdesk.infrastructure.DTOs.user.UserDTOMapper;
import com.project.helpdesk.infrastructure.gateways.user.UserEntityMapper;
import com.project.helpdesk.infrastructure.gateways.user.UserRepositoryGateway;
import com.project.helpdesk.infrastructure.persistence.user.UserRepository;

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
    ListAllUsersInteractor listAllUsersInteractor(UserGateway userGateway) {
        return new ListAllUsersInteractor(userGateway);
    }

    @Bean
    UpdateUserInteractor updateUserInteractor(UserGateway userGateway) {
        return new UpdateUserInteractor(userGateway);
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
