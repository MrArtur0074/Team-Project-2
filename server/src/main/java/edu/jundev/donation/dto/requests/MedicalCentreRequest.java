package edu.jundev.donation.dto.requests;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalCentreRequest {
    @NonNull
    private String name;

    @NonNull
    private String location;

    @NonNull
    private Long regionId;

}
