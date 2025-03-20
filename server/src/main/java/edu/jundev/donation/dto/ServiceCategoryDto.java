package edu.jundev.donation.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceCategoryDto {
    private Long id;
    private String nameOfCategory;
    private String imageUrl;
    private Integer price;
}
