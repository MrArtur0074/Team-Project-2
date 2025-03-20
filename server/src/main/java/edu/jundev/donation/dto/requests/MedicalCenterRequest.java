package edu.jundev.donation.dto.requests;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalCenterRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String location;

    @NotNull
    private Long regionId;
}
