package com.sharpness.user_service.application.service;

import lombok.Data;

@Data
public class RefreshAccessTokenResponseDto {
    public RefreshAccessTokenResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

    String accessToken;
}
