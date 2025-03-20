package edu.jundev.donation.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonationRequest {
    @NotBlank
    private String email;

    @NotNull
    private BigDecimal bloodVolume;

    @NotNull
    private Long medicalCenterId;
}
