package com.sharpness.user_service.application.service;

import com.sharpness.user_service.application.dto.*;
import com.sharpness.user_service.domain.user.CustomUser;
import com.sharpness.user_service.domain.user.CustomUserProfile;
import com.sharpness.user_service.infrastructure.repository.CustomUserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private CustomUserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Transactional
    public ResponseEntity<?> register(CreateCustomUserDTO createCustomUserDTO) {

        CustomUser existingUserByEmail = repository.findByEmail(createCustomUserDTO.getEmail()).orElse(null);
        CustomUser existingUserByUsername = repository.findByUsername(createCustomUserDTO.getUsername()).orElse(null);

        List<String> errors = new ArrayList<>();

        if (existingUserByEmail != null) {
            errors.add("User with this email is already registered.");
        }
        if (existingUserByUsername != null) {
            errors.add("User with this username is already registered.");
        }
        if (!createCustomUserDTO.getEmail().endsWith("gmail.com")){
            errors.add("Email must be gmail.com");
        }

        if (!errors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        CustomUser user = convertToEntity(createCustomUserDTO);
        user.setPassword(this.passwordEncoder.encode(createCustomUserDTO.getPassword()));
        repository.save(user);
        String accessToken = jwtService.generateAccessToken(user.getUsername(), user.getEmail(), user.getUuid());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername(), user.getEmail(), user.getUuid());
        return ResponseEntity.ok(new AuthorizationResponseDto(accessToken, refreshToken));

    }

    @Transactional
    public ResponseEntity<?> login(LoginDto loginDto) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword() ));

        CustomUser user = repository.findByUsername(loginDto.getUsername()).orElse(null);

        if (authentication.isAuthenticated()){
            assert user != null;
            String accessToken = jwtService.generateAccessToken(user.getUsername(), user.getEmail(), user.getUuid());
            String refreshToken = jwtService.generateRefreshToken(user.getUsername(), user.getEmail(), user.getUuid());
            return ResponseEntity.ok(new AuthorizationResponseDto(accessToken, refreshToken));

        } else {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }

    @Transactional
    public ResponseEntity<?> getUserByToken(String token) {
        Claims claims = jwtService.extractAllClaims(token);

        List<String> errors = new ArrayList<>();

        if (claims == null) {
            errors.add("User doesn't exist");
        }

        if (!errors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        String uuid = claims.get("uuid", String.class);
        String email = claims.get("email", String.class);
        String username = claims.get("username", String.class);

        UserByTokenDto userByTokenDto = new UserByTokenDto(username, email, uuid);
        return ResponseEntity.ok(userByTokenDto);
    }

    @Transactional
    public ResponseEntity<?> getUserByUuid(UserByUuidRequestDto dto) {

        CustomUser user = repository.findByUuid(UUID.fromString(dto.getUuid())).orElse(null);

        List<String> errors = new ArrayList<>();

        if (user == null) {
            errors.add("User doesn't exist");
        }

        if (!errors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        return ResponseEntity.ok(userMapToDto(user));
    }

    @Transactional
    public ResponseEntity<?> getProfile(String token) {
        String username = jwtService.extractUsername(token);

        List<String> errors = new ArrayList<>();

        if (username == null) {
            errors.add("User doesn't exist");
        }
        if (!errors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        CustomUser user = repository.findByUsername(username).orElse(null);

        assert user != null;
        return ResponseEntity.ok(userMapToProfileDto(user));
    }

    @Transactional
    public Boolean isUsernameUnique(UsernameCheckDto dto) {
        CustomUser user = repository.findByUsername(dto.getUsername()).orElse(null);
        return user == null;
    }

    @Transactional
    public Boolean isEmailUnique(EmailCheckDto dto) {
        CustomUser user = repository.findByEmail(dto.getEmail()).orElse(null);
        return user == null;
    }

    @Transactional
    public ResponseEntity<?> deleteUser(String uuid) {
        CustomUser user = repository.findByUuid(UUID.fromString(uuid)).orElse(null);
        List<String> errors = new ArrayList<>();
        if (user == null) {
            errors.add("User doesn't exist");
        }
        repository.deleteById(user != null ? user.getId() : 0);

        if (!errors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        return ResponseEntity.ok("User deleted");
    }

    //    Преобразование DTO в Entity
    private CustomUser convertToEntity(CreateCustomUserDTO createCustomUserDTO) {
        CustomUser user = new CustomUser();
        user.setUsername(createCustomUserDTO.getUsername());
        user.setEmail(createCustomUserDTO.getEmail());
        CustomUserProfile newUserProfile = new CustomUserProfile(
                "User",
                createCustomUserDTO.getName(),
                createCustomUserDTO.getSurname(),
                createCustomUserDTO.getDateOfBirth(),
                createCustomUserDTO.getBloodType(),
                createCustomUserDTO.getGender()
        );
        user.updateProfile(newUserProfile);
        user.setPassword(createCustomUserDTO.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return user;
    }

    private UserByUuidDto userMapToDto(CustomUser user) {
        return new UserByUuidDto(
                user.getUuid(),
                user.getEmail(),
                user.getUsername(),
                new UserProfileByUuidDto(user.getProfile().getName(), user.getProfile().getSurname())
        );
    }

    private UserMyProfileResponseDto userMapToProfileDto(CustomUser user) {
        return new UserMyProfileResponseDto(
                user.getEmail(),
                user.getUsername(),
                new UserMyProfileProfileDto(user.getProfile().getName(), user.getProfile().getSurname(),
                        user.getProfile().getDateOfBirth(), user.getProfile().getBloodType(), user.getProfile().getGender())
        );
    }

}
