package com.dashtap.DASHTAP.controller;

import com.dashtap.DASHTAP.dto.SignupRequest;
import com.dashtap.DASHTAP.dto.UserDTO;
import com.dashtap.DASHTAP.services.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){

        UserDTO createdUserDTO = authService.createUser(signupRequest);
        if (createdUserDTO == null) return new ResponseEntity<>
                ("User not created, Come again later", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
    }

}
