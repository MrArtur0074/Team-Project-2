package edu.jundev.donation.service;

import edu.jundev.donation.dto.statistic.BloodTypeStatistic;
import edu.jundev.donation.dto.statistic.CoinStatistic;
import edu.jundev.donation.dto.statistic.GenderStatistic;
import edu.jundev.donation.repository.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final DonationRepository donationRepository;

    public List<BloodTypeStatistic> findBloodStatistic() {
        return donationRepository.getStatisticByBlood();
    }

    public List<GenderStatistic> findGenderStatistic() {
        return donationRepository.getStatisticByGender();
    }

    public List<CoinStatistic> findCoinStatistic() {
        return donationRepository.getStatisticByCoin();
    }

}
