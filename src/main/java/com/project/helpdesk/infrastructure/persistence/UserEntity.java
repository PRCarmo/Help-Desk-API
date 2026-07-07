package com.project.helpdesk.infrastructure.persistence;

import com.project.helpdesk.domain.enums.RoleEnum;

import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    private String name;

    private String password;

    private RoleEnum role;

    public UserEntity(String name, String password, RoleEnum role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }
}
