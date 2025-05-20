package com.sharpness.user_service.application.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;


@Data
public class CreateNewsDTO {

    @NotNull(message = "Title can't be null")
    @Size(min = 1, max = 10000, message = "Title should be between 1 and 10 000 characters")
    private String title;

    @NotNull(message = "Description can't be null")
    @Size(min = 1, max = 10000, message = "Description should be between 1 and 10 000 characters")
    private String description;

    @NotNull(message = "Image can't be null")
    @Size(min = 1, max = 10000, message = "Image should be between 1 and 10 000 characters")
    private String image;

    @NotNull(message = "Date can't be null")
    @Size(min = 1, max = 10000, message = "Date should be between 1 and 10 000 characters")
    private String date;
}
