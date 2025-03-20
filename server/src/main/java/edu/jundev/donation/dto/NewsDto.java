package edu.jundev.donation.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsDto {
    private Long id;

    private String title;

    private String description;

    private String text;
    private LocalDateTime createdDate;
    private String imageUrl;
}
