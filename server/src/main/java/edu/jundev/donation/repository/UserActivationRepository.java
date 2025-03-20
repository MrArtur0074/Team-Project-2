package edu.jundev.donation.repository;

import edu.jundev.donation.entity.UserActivation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserActivationRepository extends JpaRepository<UserActivation, Long> {
    Optional<UserActivation> findByCodeAndEmail(String code, String email);
}
