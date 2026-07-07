package com.project.helpdesk.infrastructure.controllers.DTOs;

import com.project.helpdesk.domain.enums.RoleEnum;

public record CreateUserRequest(
    
    String name, 
    String password, 
    RoleEnum role

) {}
