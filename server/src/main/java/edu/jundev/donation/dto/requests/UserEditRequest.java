package edu.jundev.donation.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String surname;

    @NotNull
    private Long genderId;

    @NotNull
    private Long bloodTypeId;
    @NotNull
    private Integer phoneNumber;
    @NotNull
    private Long  regionId;
    @NotNull
    private LocalDate birthDate;
    private MultipartFile avatar;
}
