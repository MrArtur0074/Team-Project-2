package edu.jundev.donation.repository;

import edu.jundev.donation.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service,Long> {
    Optional<Service> findById(Long id);
}
