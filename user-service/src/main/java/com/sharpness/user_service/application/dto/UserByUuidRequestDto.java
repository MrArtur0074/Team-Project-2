package com.sharpness.user_service.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserByUuidRequestDto {

    public UserByUuidRequestDto(String uuid) {
        this.uuid = uuid;
    }

    @NotEmpty(message = "UUID should not be empty")
    @Size(min = 36, max = 36, message = "UUID should be valid")
    public String uuid;
}
