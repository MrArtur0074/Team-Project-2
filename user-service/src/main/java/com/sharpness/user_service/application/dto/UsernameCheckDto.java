package com.sharpness.user_service.application.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UsernameCheckDto {
    @NotEmpty(message = "Username should not be empty")
    String username;
}
