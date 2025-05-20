package com.sharpness.user_service.domain.user;


import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.Objects;

@Data
@Embeddable
public class CustomUserProfile {

    public CustomUserProfile() {}

    public CustomUserProfile(String role, String name, String surname, String dateOfBirth, String bloodType, String gender) {
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
        this.gender = gender;
    }

    private String role;
    private String name;
    private String surname;
    private String dateOfBirth;
    private String bloodType;
    private String gender;
}
