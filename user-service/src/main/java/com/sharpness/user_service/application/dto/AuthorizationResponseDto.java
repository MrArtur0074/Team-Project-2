package com.sharpness.user_service.application.dto;
import lombok.Data;

@Data
public class AuthorizationResponseDto {
    public AuthorizationResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    String accessToken;
    String refreshToken;
}
