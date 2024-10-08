package com.dashtap.DASHTAP.dto;

import lombok.Data;

@Data
public class SignupRequest {

    private String name;

    private String email;

    private String phone;

    private String NIC;

    private String password;

}
