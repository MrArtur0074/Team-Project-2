package com.sharpness.user_service.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateCustomUserDTO {

    @Email(message = "Email must be a valid address")
    @NotEmpty(message = "Email must not be empty")
    private String email;

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotEmpty(message = "Name must not be empty")
    private String name;

    @NotEmpty(message = "Surname must not be empty")
    private String surname;

    @NotEmpty(message = "Date of birth must not be empty")
    private String dateOfBirth;

    @NotEmpty(message = "Blood type must not be empty")
    private String bloodType;

    @NotEmpty(message = "Gender must not be empty")
    private String gender;

}



