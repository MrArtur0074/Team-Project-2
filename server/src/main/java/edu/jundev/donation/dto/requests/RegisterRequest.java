package edu.jundev.donation.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 5, max = 50)
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotNull
    private Long genderId;

    @NotNull
    private Long bloodTypeId;
}
