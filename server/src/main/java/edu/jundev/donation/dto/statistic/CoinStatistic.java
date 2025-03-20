package edu.jundev.donation.dto.statistic;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoinStatistic {
    private String name;
    private Integer queue;
    private Double percent;
}
