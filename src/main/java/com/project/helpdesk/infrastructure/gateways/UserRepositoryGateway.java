package com.project.helpdesk.infrastructure.gateways;

import com.project.helpdesk.application.gateways.UserGateway;
import com.project.helpdesk.domain.entities.User;
import com.project.helpdesk.infrastructure.persistence.UserEntity;
import com.project.helpdesk.infrastructure.persistence.UserRepository;
import com.project.helpdesk.infrastructure.gateways.UserEntityMapper;

public class UserRepositoryGateway implements UserGateway {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public UserRepositoryGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public User createUser(User userDomainObj) {
        UserEntity userEntity = userEntityMapper.toEntity(userDomainObj);
        UserEntity savedObj = userRepository.save(userEntity);
        return userEntityMapper.toDomainObj(savedObj);
    }
    
}
