package com.sharpness.user_service.application.service;

import com.sharpness.user_service.application.dto.CreateNewsDTO;
import com.sharpness.user_service.domain.entity.News;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NewsService {
    List<News> getNews();

    ResponseEntity<?> getNewsById(Long id);
    ResponseEntity<?> createNews(String token, CreateNewsDTO CreateNewsDTO);
    ResponseEntity<?> deleteNews(String token, Long id);

}
