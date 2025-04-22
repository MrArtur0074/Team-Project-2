package edu.jundev.donation.dto.response;

import edu.jundev.donation.dto.BloodTypeDto;
import edu.jundev.donation.dto.GenderDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseJwt {
    private Long id;

    private String firstName;

    private String lastName;


    private BloodTypeDto bloodType;

    private GenderDto gender;

    private String email;

    private String avatarUrl;

    private String token;

    private String tokenType;
}
