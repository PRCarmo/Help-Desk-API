package com.project.helpdesk.infrastructure.DTOs.user;

import com.project.helpdesk.domain.enums.RoleEnum;

public record CreateUserResponse(String name, RoleEnum role) {
    
}
