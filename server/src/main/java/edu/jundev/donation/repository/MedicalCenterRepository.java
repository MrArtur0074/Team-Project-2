package edu.jundev.donation.repository;

import edu.jundev.donation.entity.MedicalCenter;
import edu.jundev.donation.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalCenterRepository extends JpaRepository<MedicalCenter, Long> {
    List<MedicalCenter> findByRegion(Region region);
}
