package edu.jundev.donation.service;

import edu.jundev.donation.dto.StatusDto;
import edu.jundev.donation.mapper.StatusMapper;
import edu.jundev.donation.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatusService {
    private final StatusRepository statusRepository;
    private final StatusMapper statusMapper;

    public List<StatusDto> findAll() {
        var statuses = statusRepository.findAll();
        return statuses.stream()
                .map(statusMapper::toDto)
                .collect(Collectors.toList());
    }
}
