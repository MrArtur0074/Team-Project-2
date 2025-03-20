package edu.jundev.donation.dto.statistic;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodTypeStatistic {
    private String bloodType;
    private Double percent;
}
