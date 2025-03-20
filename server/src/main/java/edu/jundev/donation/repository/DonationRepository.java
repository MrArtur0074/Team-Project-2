package edu.jundev.donation.repository;

import edu.jundev.donation.entity.Donation;
import edu.jundev.donation.entity.User;
import edu.jundev.donation.repository.custom.CustomDonationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long>, CustomDonationRepository {
    Page<Donation> findByUser(User user, Pageable pageable);
    Page<Donation> findByOrderByCreatedAtDesc(Pageable pageable);
}