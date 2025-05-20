package com.sharpness.user_service.application.service;

import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface JwtService {
    String generateAccessToken(String username, String email, UUID uuid);
    String generateRefreshToken(String username, String email, UUID uuid);
    String extractUsername(String token);
    ResponseEntity<?> refreshAccessToken(String token);
    boolean validateAccessToken(String token, UserDetails userDetails);
    boolean validateRefreshToken(String token, UserDetails userDetails);
    Claims extractAllClaims(String token);
}
