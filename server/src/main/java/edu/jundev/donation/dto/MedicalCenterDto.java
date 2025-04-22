package edu.jundev.donation.dto;

import edu.jundev.donation.entity.Region;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalCenterDto {
    private Long id;
    private String name;
    private String location;
    private Region region;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
