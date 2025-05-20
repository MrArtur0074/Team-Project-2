package com.sharpness.user_service.presentation;


import com.sharpness.user_service.application.dto.CreateNewsDTO;
import com.sharpness.user_service.application.dto.UserByUuidDto;
import com.sharpness.user_service.application.dto.UserByUuidRequestDto;
import com.sharpness.user_service.application.service.NewsService;
import com.sharpness.user_service.domain.entity.News;
import com.sharpness.user_service.domain.user.CustomUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/news-api")
@Validated
public class NewsController {

    @Autowired
    private NewsService newsService;



    @Operation(summary = "Get all news")
    @GetMapping("/news")
    public List<News> getNews() {
        return newsService.getNews();
    }

    @Operation(summary = "Get news by id")
    @GetMapping("/news/{id}")
    public ResponseEntity<?> getNewsById(@Valid @PathVariable Long id) {
        return newsService.getNewsById(id);
    };

    @Operation(summary = "Create news")
    @PostMapping("/news/create")
    public ResponseEntity<?> createNews(@Valid @RequestBody CreateNewsDTO dto,
                                        @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        return newsService.createNews(token, dto);
    };

    @Operation(summary = "delete news by id")
    @DeleteMapping("/news/{id}")
    public ResponseEntity<?> deleteNews(@Valid @PathVariable Long id,
                                        @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        return newsService.deleteNews(token, id);
    };

}
