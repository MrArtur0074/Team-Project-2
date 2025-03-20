package edu.jundev.donation.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String avatarUrl;
}