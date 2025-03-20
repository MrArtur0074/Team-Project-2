package edu.jundev.donation.repository;

import edu.jundev.donation.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByQueueNumber(Integer queueNumber);
    Status findTopByOrderByQueueNumberDesc();
}
