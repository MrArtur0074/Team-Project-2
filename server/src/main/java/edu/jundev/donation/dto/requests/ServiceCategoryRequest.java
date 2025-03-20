package edu.jundev.donation.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCategoryRequest {
    @NotNull
    private MultipartFile imageUrl;

    @NotBlank
    private String nameOfCategory;

    @NotBlank
    private Integer price;
}
