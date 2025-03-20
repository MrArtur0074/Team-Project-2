package edu.jundev.donation.dto.requests;

import edu.jundev.donation.entity.MedicalCenter;
import edu.jundev.donation.entity.ServiceCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequest {
    @NotNull
    private BigDecimal price;

    @NotBlank
    private String name;

    @NotNull
    private MedicalCenter medicalCenter;

    @NotNull
    private ServiceCategory categories;
}
