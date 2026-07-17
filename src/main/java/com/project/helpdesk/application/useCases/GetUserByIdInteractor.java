package com.project.helpdesk.application.useCases;

import com.project.helpdesk.application.gateways.UserGateway;
import com.project.helpdesk.domain.entities.User;

public class GetUserByIdInteractor {
 
    private final UserGateway userGateway;

    public GetUserByIdInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User getUserById(Long id) {
        return userGateway.getUserById(id);
    }

}
