package com.sharpness.user_service.application.dto;

import lombok.Data;

@Data
public class UserProfileByUuidDto {
    public UserProfileByUuidDto(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String name;
    public String surname;
}
