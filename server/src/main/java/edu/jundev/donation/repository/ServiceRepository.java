package edu.jundev.donation.repository;

import edu.jundev.donation.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Services,Long> {
    Optional<Services> findById(Long id);
}
