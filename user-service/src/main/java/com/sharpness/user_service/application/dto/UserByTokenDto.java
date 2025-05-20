package com.sharpness.user_service.application.dto;

import lombok.Data;


@Data
public class UserByTokenDto {

    public UserByTokenDto(String username, String email, String uuid) {
        this.username = username;
        this.email = email;
        this.uuid = uuid;
    }

    private String username;
    private String email;
    private String uuid;
}
