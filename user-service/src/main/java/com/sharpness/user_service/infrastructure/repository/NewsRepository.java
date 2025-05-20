package com.sharpness.user_service.infrastructure.repository;

import com.sharpness.user_service.domain.entity.News;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NewsRepository extends CrudRepository<News, Long>{
    Optional<News> findByTitle(String title);
}
