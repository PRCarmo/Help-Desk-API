package com.project.helpdesk.infrastructure.DTOs.user;

import com.project.helpdesk.domain.enums.RoleEnum;

public record UserResponse(
    
    String name, 
    RoleEnum role

) {}
