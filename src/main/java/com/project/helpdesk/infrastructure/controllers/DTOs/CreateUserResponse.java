package com.project.helpdesk.infrastructure.controllers.DTOs;

import com.project.helpdesk.domain.enums.RoleEnum;

public record CreateUserResponse(String name, RoleEnum role) {
    
}
