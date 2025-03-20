package edu.jundev.donation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodTypeDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;
}
