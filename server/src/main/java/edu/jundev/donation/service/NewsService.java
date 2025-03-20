package edu.jundev.donation.service;

import edu.jundev.donation.dto.NewsDto;
import edu.jundev.donation.dto.NewsShortDto;
import edu.jundev.donation.dto.requests.NewsRequest;
import edu.jundev.donation.entity.News;
import edu.jundev.donation.exception.FileException;
import edu.jundev.donation.exception.FileWriteException;
import edu.jundev.donation.exception.GCPFileUploadException;
import edu.jundev.donation.exception.NotFoundException;
import edu.jundev.donation.mapper.NewsMapper;
import edu.jundev.donation.repository.NewsRepository;
import edu.jundev.donation.utils.CloudStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    private final CloudStorage cloudStorage;
    private final NewsMapper newsMapper;

    public NewsDto addNews(NewsRequest newsRequest)  throws FileException, FileWriteException, GCPFileUploadException {
        if(!cloudStorage.isImageFile(newsRequest.getImage()))
            throw new FileException("Not image file has been uploaded!");
        String file = cloudStorage.uploadFile(newsRequest.getImage());
        News news = newsMapper.toEntity(newsRequest,file);
        News saved = newsRepository.save(news);
        return newsMapper.toDto(saved);
    }

    public Page<NewsShortDto> findAll(Pageable pageable) {
        var news = newsRepository.findByOrderByCreatedDateDesc(pageable);
        return news.map(newsMapper::toShortDto);
    }

    public NewsDto getNewsById(Long id) throws NotFoundException {
        News news = newsRepository.findById(id).orElseThrow(() -> new NotFoundException("News with id " + id + " not found!"));
        return newsMapper.toDto(news);
    }

    public void deleteNews(Long id) throws NotFoundException {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("News with id " + id + " not found!"));
        newsRepository.delete(news);
    }
}
