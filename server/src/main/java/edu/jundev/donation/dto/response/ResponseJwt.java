package edu.jundev.donation.dto.response;

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

    private String email;

    private String avatarUrl;

    private String token;

    private String tokenType;

    private String role;
}
