package com.project.helpdesk.infrastructure.DTOs.user;

import com.project.helpdesk.domain.enums.RoleEnum;

public record UserRequest(
    
    String name, 
    String password, 
    RoleEnum role

) {}
