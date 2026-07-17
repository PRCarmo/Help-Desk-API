package com.project.helpdesk.application.useCases;

import com.project.helpdesk.domain.entities.User;
import com.project.helpdesk.application.gateways.UserGateway;
import java.util.List;

public class ListAllUsersInteractor {
    
    private final UserGateway userGateway;

    public ListAllUsersInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public List<User> listAllUsers() {
        return userGateway.listAllUsers();
    }

}
