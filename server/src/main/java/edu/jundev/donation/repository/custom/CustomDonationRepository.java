package edu.jundev.donation.repository.custom;

import edu.jundev.donation.dto.statistic.BloodTypeStatistic;
import edu.jundev.donation.dto.statistic.CoinStatistic;
import edu.jundev.donation.dto.statistic.GenderStatistic;

import java.util.List;

public interface CustomDonationRepository {
    List<BloodTypeStatistic> getStatisticByBlood();
    List<GenderStatistic> getStatisticByGender();
    List<CoinStatistic> getStatisticByCoin();
}
