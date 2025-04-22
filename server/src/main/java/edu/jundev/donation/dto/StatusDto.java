package edu.jundev.donation.dto;

import lombok.*;

import jakarta.persistence.Column;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusDto {
    private Long id;
    private String name;
    private Integer points;
    private Integer queueNumber;
}
