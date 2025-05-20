package com.sharpness.user_service.application.service;

import com.sharpness.user_service.application.dto.CreateNewsDTO;
import com.sharpness.user_service.domain.entity.News;
import com.sharpness.user_service.infrastructure.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository repository;

    @Autowired
    private JwtService jwtService;

    @Override
    public List<News> getNews() {
        return (List<News>) repository.findAll();
    }

    @Override
    public ResponseEntity<?> getNewsById(Long id) {
        Optional<News> newsExist = repository.findById(id);

        if(newsExist.isEmpty()){
            Map<String, String> response = new HashMap<>();
            response.put("message", "Новость не найдена");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        };

        return new ResponseEntity<>(newsExist, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createNews(String token, CreateNewsDTO CreateNewsDTO) {
        Map<String, String> response = new HashMap<>();
        String username = jwtService.extractUsername(token);

        if (!username.equals("admin123")) {
            response.put("message", "У вас нет прав для создания новостей");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }

        News news = convertCreateDtoToNews(CreateNewsDTO);
        repository.save(news);

        return new ResponseEntity<>(news, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> deleteNews(String token, Long id) {
        Map<String, String> response = new HashMap<>();
        String username = jwtService.extractUsername(token);


        Optional<News> newsExist = repository.findById(id);
        if(newsExist.isEmpty()){
            response.put("message", "Новость не найдена");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        };
        if (!username.equals("admin123")) {
            response.put("message", "У вас нет прав для создания новостей");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }

        repository.deleteById(id);

        return new ResponseEntity<>("Новость удалена", HttpStatus.OK);
    }

    //    Превращение DTO в Entity
    private News convertCreateDtoToNews(CreateNewsDTO dto) {
        News news = new News();
        news.setTitle(dto.getTitle());
        news.setDescription(dto.getDescription());
        news.setImage(dto.getImage());
        news.setDate(dto.getDate());
        return news;
    }

}
