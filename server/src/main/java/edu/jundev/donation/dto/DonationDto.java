package edu.jundev.donation.dto;

import edu.jundev.donation.entity.User;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonationDto {
    private Long id;

    private UserDto user;

    private MedicalCenterDto medicalCenter;

    private BloodTypeDto bloodType;

    private BigDecimal volume;

    private LocalDateTime createdAt;

    private Integer points;
}
