package com.dashtap.DASHTAP.dto;

import com.dashtap.DASHTAP.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;

    private UserRole userRole;

    private Long userId;

}
