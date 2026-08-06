package com.project.helpdesk.domain.entities;

import com.project.helpdesk.domain.enums.RoleEnum;

public record User(
    
    String name, 
    String password, 
    RoleEnum role

) {}