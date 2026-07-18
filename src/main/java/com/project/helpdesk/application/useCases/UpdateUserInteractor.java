package com.project.helpdesk.application.useCases;

import com.project.helpdesk.application.gateways.UserGateway;
import com.project.helpdesk.domain.entities.User;

public class UpdateUserInteractor {
    
    private final UserGateway userGateway;

    public UpdateUserInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User updateUser(Long id, User user) {
        return userGateway.updateUser(id, user);
    }
}
