package com.project.helpdesk.infrastructure.persistence.user;

import com.project.helpdesk.domain.enums.RoleEnum;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "users", schema = "user")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(name = "Name")
    private String name;

    @Column(name = "Password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "Role")
    private RoleEnum role;

    public UserEntity(String name, String password, RoleEnum role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }
}
