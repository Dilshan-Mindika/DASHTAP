package com.dashtap.DASHTAP.dto;

import com.dashtap.DASHTAP.enums.UserRole;
import lombok.Data;

@Data

public class UserDTO {

    private Long id;

    private String name;

    private String email;

    private UserRole userRole;

}
