package com.sharpness.user_service.application.dto;

public class UserMyProfileResponseDto {
    public String email;
    public String username;
    public UserMyProfileProfileDto profile;

    public UserMyProfileResponseDto(String email, String username, UserMyProfileProfileDto profile) {
        this.email = email;
        this.username = username;
        this.profile = profile;
    }
}
