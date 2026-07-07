package com.project.helpdesk.application.useCases;

import com.project.helpdesk.application.gateways.UserGateway;
import com.project.helpdesk.domain.entities.User;

public class CreateUserInteractor {

    private UserGateway userGateway;

    public CreateUserInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User createUser(User user) {
        return userGateway.createUser(user);
    }
}