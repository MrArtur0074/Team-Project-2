package edu.jundev.donation.repository;

import edu.jundev.donation.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News,Long> {
    Optional<News> findById(News news);
    Page<News> findByOrderByCreatedDateDesc(Pageable pageable);
}
