package com.project.helpdesk.infrastructure.DTOs.user;

import com.project.helpdesk.domain.enums.RoleEnum;

public record CreateUserRequest(
    
    String name, 
    String password, 
    RoleEnum role

) {}
