package edu.jundev.donation.dto.statistic;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenderStatistic {
    private String gender;
    private Double percent;
}
