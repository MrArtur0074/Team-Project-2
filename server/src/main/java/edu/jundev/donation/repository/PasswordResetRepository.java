package edu.jundev.donation.repository;

import edu.jundev.donation.entity.PasswordReset;
import edu.jundev.donation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordReset, Long> {
    Optional<PasswordReset> findByUser(User user);

    Optional<PasswordReset> findByCode(String code);
}
