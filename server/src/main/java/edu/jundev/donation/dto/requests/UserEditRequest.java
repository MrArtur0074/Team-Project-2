package edu.jundev.donation.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEditRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotNull
    private Long genderId;

    @NotNull
    private Long bloodTypeId;

    @NotNull
    private String phoneNumber;

    @NotNull
    private Long  regionId;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private MultipartFile avatar;
}
