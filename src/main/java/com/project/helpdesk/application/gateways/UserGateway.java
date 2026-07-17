package com.project.helpdesk.application.gateways;

import com.project.helpdesk.domain.entities.User;
import java.util.List;

public interface UserGateway {
    
    User createUser(User user);

    void deleteUser(Long id);

    User getUserById(Long id);

    List<User> listAllUsers();
}
