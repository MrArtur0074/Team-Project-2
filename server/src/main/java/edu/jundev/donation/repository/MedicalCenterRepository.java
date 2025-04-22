package edu.jundev.donation.repository;

import edu.jundev.donation.entity.MedicalCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalCenterRepository extends JpaRepository<MedicalCenter, Long> {
}
