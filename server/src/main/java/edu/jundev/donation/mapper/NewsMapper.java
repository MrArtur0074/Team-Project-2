package edu.jundev.donation.mapper;

import edu.jundev.donation.dto.NewsDto;
import edu.jundev.donation.dto.NewsShortDto;
import edu.jundev.donation.dto.requests.NewsRequest;
import edu.jundev.donation.entity.News;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewsMapper {
    public News toEntity(NewsRequest newsRequest,String link){
       return News.builder()
                .title(newsRequest.getTitle())
                .description(newsRequest.getDescription())
                .text(newsRequest.getText())
                .imageUrl(link)
                .build();
    }
    public NewsDto toDto(News news){
        return NewsDto.builder()
                .id(news.getId())
                .title(news.getTitle())
                .description(news.getDescription())
                .text(news.getText())
                .imageUrl(news.getImageUrl())
                .createdDate(news.getCreatedDate())
                .build();
    }
    public NewsShortDto toShortDto(News news){
        return NewsShortDto.builder()
                .id(news.getId())
                .title(news.getTitle())
                .description(news.getDescription())
                .imageUrl(news.getImageUrl())
                .createdDate(news.getCreatedDate())
                .build();
    }


}
