package com.sharpness.user_service.application.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class EmailCheckDto {
    @NotEmpty(message = "Email should not be empty")
    String email;
}
