package edu.jundev.donation.repository;

import edu.jundev.donation.entity.ServiceCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory,Long> {
    Optional<ServiceCategory> findById(Long id);
    Page<ServiceCategory> findByOrderByPrice(Pageable pageable);

}
