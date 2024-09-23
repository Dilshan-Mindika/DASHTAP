package com.dashtap.DASHTAP.controller;

import com.dashtap.DASHTAP.dto.AuthenticationRequest;
import com.dashtap.DASHTAP.dto.AuthenticationResponse;
import com.dashtap.DASHTAP.dto.SignupRequest;
import com.dashtap.DASHTAP.dto.UserDTO;
import com.dashtap.DASHTAP.entity.User;
import com.dashtap.DASHTAP.repository.UserRepository;
import com.dashtap.DASHTAP.services.auth.AuthService;
import com.dashtap.DASHTAP.utills.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    private final JWTUtils jwtUtil;

    private final UserRepository userRepository;


    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){

        if (authService.hasUserWithEmail(signupRequest.getEmail()))
            return new ResponseEntity<>("User already exist with this email", HttpStatus.NOT_ACCEPTABLE);

        UserDTO createdUserDTO = authService.createUser(signupRequest);
        if (createdUserDTO == null) return new ResponseEntity<>
                ("User not created, Come again later", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws
            BadCredentialsException,
            DisabledException,
            UsernameNotFoundException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (optionalUser.isPresent()) {
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
        }
        return authenticationResponse;
    }

}
