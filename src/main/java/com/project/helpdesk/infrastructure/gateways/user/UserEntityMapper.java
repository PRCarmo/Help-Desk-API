package com.project.helpdesk.infrastructure.gateways.user;

import java.util.List;

import com.project.helpdesk.domain.entities.User;
import com.project.helpdesk.infrastructure.persistence.user.UserEntity;

public class UserEntityMapper {

    UserEntity toEntity(User userDomainObj) {
        return new UserEntity(
            userDomainObj.name(), 
            userDomainObj.password(), 
            userDomainObj.role()
        );
    }

    User toDomainObj(UserEntity userEntity) {
        return new User(
            userEntity.getName(), 
            userEntity.getPassword(), 
            userEntity.getRole()
        );
    }

    public List<User> toDomainList(List<UserEntity> entities) {
    return entities.stream()
            .map(this::toDomainObj)
            .toList();
    }
}
