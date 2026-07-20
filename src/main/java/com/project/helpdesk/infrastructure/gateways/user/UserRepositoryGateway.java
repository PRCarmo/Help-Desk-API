package com.project.helpdesk.infrastructure.gateways.user;

import java.util.List;
import java.util.stream.Collectors;

import com.project.helpdesk.application.gateways.UserGateway;
import com.project.helpdesk.domain.entities.User;
import com.project.helpdesk.infrastructure.persistence.user.UserEntity;
import com.project.helpdesk.infrastructure.persistence.user.UserRepository;

public class UserRepositoryGateway implements UserGateway {

    private final UserRepository repository;
    private final UserEntityMapper entityMapper;

    public UserRepositoryGateway(UserRepository repository, UserEntityMapper entityMapper) {
        this.repository = repository;
        this.entityMapper = entityMapper;
    }

    @Override
    public User createUser(User userDomainObj) {
        UserEntity user = entityMapper.toEntity(userDomainObj);

        Long userId = user.getId();

        if (repository.existsById(userId)) {

            throw new ResourceAlreadyExistsException("User with ID " + userId + " already exists"); 
        
        } else {

            UserEntity savedUser = repository.save(user);
            
            return entityMapper.toDomainObj(savedUser);
        
        }
    }

    @Override 
    public void deleteUser(Long id) {
        UserEntity user = repository.findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException("User with ID " + id + " not found")
            );
            
        repository.delete(user);
    }

    @Override
    public User getUserById(Long id) {
        UserEntity foundUser = 
        repository.findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException("User with ID " + id + " not found")
            );
        
        return entityMapper.toDomainObj(foundUser);
    }

    @Override
    public List<User> listAllUsers() {
        List<UserEntity> userEntities = repository.findAll();
        List<User> users = 
            userEntities
                .stream()
                .map(entityMapper::toDomainObj)
                .collect(Collectors.toList());
                    
        return users;
    }

    @Override
    public User updateUser(Long id, User user) {
        UserEntity requestedUser = 
            repository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException("User with ID " + id + " not found")
                );

        UserEntity updateInfo = entityMapper.toEntity(user);

        requestedUser.setName(updateInfo.getName());
        requestedUser.setPassword(updateInfo.getPassword());
        requestedUser.setRole(updateInfo.getRole());

        repository.save(requestedUser);

        return entityMapper.toDomainObj(requestedUser);
    }
    
}
