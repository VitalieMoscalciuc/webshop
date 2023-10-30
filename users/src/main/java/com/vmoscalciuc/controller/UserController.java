package com.vmoscalciuc.controller;

import com.vmoscalciuc.dto.LoginRequest;
import com.vmoscalciuc.dto.RegistrationRequest;
import com.vmoscalciuc.dto.RegistrationResponse;
import com.vmoscalciuc.model.UserEntity;
import com.vmoscalciuc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/public")
public class UserController {

    private final UserService userService;


    @PostMapping("/login")
    public RegistrationResponse loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @PostMapping("/register")
    public RegistrationResponse registerUser(@RequestBody RegistrationRequest registrationRequest) {
        return userService.registerUser(registrationRequest.getUsername(), registrationRequest.getPassword());
    }
}
