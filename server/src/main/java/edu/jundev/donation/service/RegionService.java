package edu.jundev.donation.service;

import edu.jundev.donation.dto.RegionDto;
import edu.jundev.donation.mapper.RegionMapper;
import edu.jundev.donation.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    public List<RegionDto> findAll() {
        var cities = regionRepository.findAll();
        return cities.stream()
                .map(regionMapper::toDto)
                .collect(Collectors.toList());
    }
}
