package com.dashtap.DASHTAP.services.auth;


import com.dashtap.DASHTAP.dto.SignupRequest;
import com.dashtap.DASHTAP.dto.UserDTO;

public interface AuthService {

    UserDTO createUser(SignupRequest signupRequest);

    boolean hasUserWithEmail(String email);
}
