package edu.jundev.donation.repository;

import edu.jundev.donation.entity.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BloodTypeRepository extends JpaRepository<BloodType,Long> {
    Optional<BloodType> findBloodTypeByName(String name);
}
