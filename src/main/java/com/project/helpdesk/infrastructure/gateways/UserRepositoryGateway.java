package com.project.helpdesk.infrastructure.gateways;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override 
    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException("User with ID " + id + " not found")
            );
            
        userRepository.delete(user);
    }

    @Override
    public User getUserById(Long id) {
        UserEntity foundUser = 
        userRepository.findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException("User with ID " + id + " not found")
            );
            
        return userEntityMapper.toDomainObj(foundUser);
    }

    @Override
    public List<User> listAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<User> users = 
            userEntities
                .stream()
                .map(userEntityMapper::toDomainObj)
                    .collect(Collectors.toList());
                    
        return users;
    }

    @Override
    public User updateUser(Long id, User user) {
        UserEntity requestedUser = 
            userRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException("User with ID " + id + " not found")
                );

        UserEntity updateInfo = userEntityMapper.toEntity(user);

        requestedUser.setName(updateInfo.getName());
        requestedUser.setPassword(updateInfo.getPassword());
        requestedUser.setRole(updateInfo.getRole());

        userRepository.save(requestedUser);

        User updatedUser = userEntityMapper.toDomainObj(requestedUser);

        return updatedUser;
    }

    // Corrigir problemas de vazamento de dados com DTOs nesses métodos (e no ticket também).
    // TODO: Lembrar de implementar tratamento de erros nesses métodos
    // TODO: Lembrar de padronizar o código desses métodos (Ex: adaptar createUser para o modelo de deleteUser) */
    
}
