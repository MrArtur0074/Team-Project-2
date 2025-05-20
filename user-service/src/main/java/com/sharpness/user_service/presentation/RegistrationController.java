package com.sharpness.user_service.presentation;

import com.sharpness.user_service.application.dto.CreateCustomUserDTO;
import com.sharpness.user_service.application.dto.EmailCheckDto;
import com.sharpness.user_service.application.dto.LoginDto;
import com.sharpness.user_service.application.dto.UsernameCheckDto;
import com.sharpness.user_service.application.service.CustomUserDetailService;
import com.sharpness.user_service.application.service.UserService;
import com.sharpness.user_service.infrastructure.repository.CustomUserRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@Validated
public class RegistrationController {


    @Autowired
    private CustomUserRepository customUserRepository;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateCustomUserDTO createCustomUserDTO) {
        return userService.register(createCustomUserDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginCredentials) {
        return userService.login(loginCredentials);
    };

    @Operation(summary = "Check if username is unique")
    @PostMapping("/check-username")
    public boolean checkUsername(@Valid @RequestBody() UsernameCheckDto dto) {
        return userService.isUsernameUnique(dto);
    }

    @Operation(summary = "Check if email is unique")
    @PostMapping("/check-email")
    public boolean checkEmail(@Valid @RequestBody() EmailCheckDto dto) {
        return userService.isEmailUnique(dto);
    }

}
