package com.sharpness.user_service.application.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserByUuidDto {

    public UserByUuidDto(UUID uuid, String email, String username, UserProfileByUuidDto profile) {
        this.uuid = uuid.toString();
        this.email = email;
        this.username = username;
        this.profile = profile;
    }


    public String uuid;
    public String email;
    public String username;
    public UserProfileByUuidDto profile;
}
