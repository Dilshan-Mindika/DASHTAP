package com.dashtap.DASHTAP.services.auth;

import com.dashtap.DASHTAP.dto.SignupRequest;
import com.dashtap.DASHTAP.dto.UserDTO;
import com.dashtap.DASHTAP.entity.User;
import com.dashtap.DASHTAP.enums.UserRole;
import com.dashtap.DASHTAP.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public UserDTO createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        user.setUserRole(UserRole.USER);
        User createedUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(createedUser.getId());

        return userDTO;
    }

    @Override
    public boolean hasUserWithEmail(String email) {
        return false;
    }

}
