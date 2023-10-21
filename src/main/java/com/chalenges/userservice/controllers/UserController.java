package com.chalenges.userservice.controllers;

import com.chalenges.userservice.request.UserRequest;
import com.chalenges.userservice.responses.RegisterUserResponse;
import com.chalenges.userservice.services.impl.RegisterUserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author lady Cuizara
 */
@Validated
@RestController
@RequestMapping("/api")
public class UserController {
    private RegisterUserServiceImpl registerUserServiceImpl;

    public UserController(RegisterUserServiceImpl registerUserServiceImpl) {
        this.registerUserServiceImpl = registerUserServiceImpl;
    }

    @PostMapping("/users")
    public ResponseEntity<RegisterUserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        RegisterUserResponse response = registerUserServiceImpl.registerUser(userRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
