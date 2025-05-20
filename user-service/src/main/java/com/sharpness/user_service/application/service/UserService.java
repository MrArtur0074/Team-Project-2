package com.sharpness.user_service.application.service;

import com.sharpness.user_service.application.dto.*;
import org.springframework.http.ResponseEntity;



public interface UserService {
    ResponseEntity<?>  register(CreateCustomUserDTO createCustomUserDTO);
    ResponseEntity<?> login(LoginDto loginDto);
    ResponseEntity<?> getUserByToken(String token);
    ResponseEntity<?> getUserByUuid(UserByUuidRequestDto dto);
    ResponseEntity<?> getProfile(String token);
    Boolean isUsernameUnique(UsernameCheckDto dto);
    Boolean isEmailUnique(EmailCheckDto dto);
    ResponseEntity<?> deleteUser(String uuid);
}
