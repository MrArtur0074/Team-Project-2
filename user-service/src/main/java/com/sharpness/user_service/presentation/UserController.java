package com.sharpness.user_service.presentation;

import com.sharpness.user_service.application.dto.*;
import com.sharpness.user_service.application.service.JwtService;
import com.sharpness.user_service.application.service.UserService;
import com.sharpness.user_service.domain.user.CustomUser;
import com.sharpness.user_service.infrastructure.repository.CustomUserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/user-api")
@Validated
public class UserController {

    @Autowired
    private CustomUserRepository customUserRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Get all users")
    @GetMapping("/users")
    public List<CustomUser> getUsers() {
        return customUserRepository.findAll();
    }

    @Operation(summary = "Delete user by uuid")
    @DeleteMapping("/user/{uuid}")
    public ResponseEntity<?> deleteUser(@PathVariable String uuid) {
        return userService.deleteUser(uuid);
    }

    @Operation(summary = "Get user by token")
    @GetMapping("/user-by-token")
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserByTokenDto.class)))
    public ResponseEntity<?> getUserByToken(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        return userService.getUserByToken(token);
    }

    @Operation(summary = "Get user by uuid")
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserByUuidDto.class)))
    @GetMapping("/user-by-uuid")
    public ResponseEntity<?> getUserByUuid(@Valid @RequestBody() UserByUuidRequestDto dto) {

        return userService.getUserByUuid(dto);
    };

    @Operation(summary = "Get profile")
    @GetMapping("/profile")
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserMyProfileResponseDto.class)))
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        return userService.getProfile(token);
    }


}
