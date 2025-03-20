package edu.jundev.donation.controller;

import edu.jundev.donation.dto.NewsDto;
import edu.jundev.donation.dto.NewsShortDto;
import edu.jundev.donation.dto.requests.NewsRequest;
import edu.jundev.donation.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @PostMapping("/create")
    public ResponseEntity<NewsDto> createNews(@Valid @ModelAttribute NewsRequest newsRequest){
        return ResponseEntity.ok(newsService.addNews(newsRequest));
    }

    @GetMapping
    public ResponseEntity<Page<NewsShortDto>> findAll(@PageableDefault(size = 6, page = 0) Pageable pageable) {
        return ResponseEntity.ok(newsService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> getNewsById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(newsService.getNewsById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteNewsById(@PathVariable(name = "id") Long id){
        newsService.deleteNews(id);
        return ResponseEntity.ok("News has been successfully deleted");
    }
}
