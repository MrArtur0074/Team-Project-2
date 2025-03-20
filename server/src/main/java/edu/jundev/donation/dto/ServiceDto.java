package edu.jundev.donation.dto;

import edu.jundev.donation.entity.MedicalCenter;
import edu.jundev.donation.entity.ServiceCategory;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private MedicalCenter medicalCenter;
    private ServiceCategory serviceCategory;
}
