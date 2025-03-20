package edu.jundev.donation.repository.custom.impl;

import edu.jundev.donation.dto.statistic.BloodTypeStatistic;
import edu.jundev.donation.dto.statistic.CoinStatistic;
import edu.jundev.donation.dto.statistic.GenderStatistic;
import edu.jundev.donation.repository.custom.CustomDonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomDonationRepositoryImpl implements CustomDonationRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<BloodTypeStatistic> getStatisticByBlood() {
        String sql = "SELECT b.name AS bloodType,\n" +
                "       COALESCE(SUM(d.volume), 0.0)\n" +
                "           * 100\n" +
                "           / (SELECT SUM(d1.volume)\n" +
                "              FROM donations AS d1) AS percent\n" +
                "FROM donations AS d\n" +
                "RIGHT JOIN blood_types AS b ON b.id = d.blood_type_id\n" +
                "GROUP BY b.name\n" +
                "ORDER BY b.name DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BloodTypeStatistic.class));
    }

    @Override
    public List<GenderStatistic> getStatisticByGender() {
        String sql = "SELECT g.name AS gender,\n" +
                "       COUNT(d.id) * 100\n" +
                "           / (SELECT COUNT(d1.id)\n" +
                "              FROM donations d1) AS percent\n" +
                "FROM genders AS g\n" +
                "    LEFT JOIN users_info AS uf ON uf.gender_id = g.id\n" +
                "    LEFT JOIN users AS u ON u.id = uf.user_id\n" +
                "LEFT JOIN donations AS d ON d.user_id = u.id\n" +
                "GROUP BY g.name";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GenderStatistic.class));
    }

    @Override
    public List<CoinStatistic> getStatisticByCoin() {
        String sql = "SELECT s.name AS status,\n" +
                "       s.queues AS queue,\n" +
                "       COUNT(d.id) * 100\n" +
                "           / (SELECT COUNT(d1.id)\n" +
                "              FROM donations d1) AS percent\n" +
                "FROM statuses AS s\n" +
                "LEFT JOIN users_info AS uf ON uf.status_id = s.id\n" +
                "LEFT JOIN users AS u ON u.id = uf.user_id\n" +
                "LEFT JOIN donations AS d ON d.user_id = u.id\n" +
                "GROUP BY s.name, s.queues\n" +
                "ORDER BY s.queues";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CoinStatistic.class));
    }
}
