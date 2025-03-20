package edu.jundev.donation.controller;

import edu.jundev.donation.dto.statistic.BloodTypeStatistic;
import edu.jundev.donation.dto.statistic.CoinStatistic;
import edu.jundev.donation.dto.statistic.GenderStatistic;
import edu.jundev.donation.service.DonationService;
import edu.jundev.donation.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticService statisticService;

    @GetMapping("/blood")
    public ResponseEntity<List<BloodTypeStatistic>> getStatisticsBlood() {
        return ResponseEntity.ok(statisticService.findBloodStatistic());
    }

    @GetMapping("/gender")
    public ResponseEntity<List<GenderStatistic>> getStatisticsGender() {
        return ResponseEntity.ok(statisticService.findGenderStatistic());
    }

    @GetMapping("/coin")
    public ResponseEntity<List<CoinStatistic>> getStatisticsCoin() {
        return ResponseEntity.ok(statisticService.findCoinStatistic());
    }
}
