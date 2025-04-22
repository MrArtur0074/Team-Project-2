package edu.jundev.donation.dto;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDto {
    private Long id;

    private BloodTypeDto bloodTypeDto;

    private GenderDto genderDto;

    private Integer points;

    private StatusDto statusDto;

    private UserDto userDto;

    private Integer amountOfDonations;
    private Integer phoneNumber;
    private RegionDto regionDto;
}
