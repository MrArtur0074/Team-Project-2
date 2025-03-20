package edu.jundev.donation.dto;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDto {
    private Long id;

    private BloodTypeDto bloodType;

    private GenderDto gender;

    private Integer points;

    private StatusDto status;

    private UserDto user;

    private Integer amountOfDonations;

    private String phoneNumber;

    private RegionDto region;

    private Integer age;
}
