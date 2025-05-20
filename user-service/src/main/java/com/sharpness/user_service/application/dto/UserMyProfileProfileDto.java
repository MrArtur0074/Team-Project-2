package com.sharpness.user_service.application.dto;

import lombok.Data;

@Data
public class UserMyProfileProfileDto {
    public UserMyProfileProfileDto(String name, String surname, String dateOfBirth, String bloodType, String gender) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
        this.gender = gender;
    }

    public String name;
    public String surname;
    public String dateOfBirth;
    public String bloodType;
    public String gender;
}
