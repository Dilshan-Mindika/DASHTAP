package com.dashtap.DASHTAP.entity;

import com.dashtap.DASHTAP.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private UserRole role;

    public void setUserRole(UserRole userRole) {
    }
}
