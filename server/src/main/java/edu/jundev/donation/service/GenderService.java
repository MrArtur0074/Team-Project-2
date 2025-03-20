package edu.jundev.donation.service;

import edu.jundev.donation.dto.GenderDto;
import edu.jundev.donation.mapper.GenderMapper;
import edu.jundev.donation.repository.GenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenderService {
    private final GenderRepository genderRepository;
    private final GenderMapper genderMapper;

    public List<GenderDto> findAll() {
        var genders = genderRepository.findAll();
        return genders.stream()
                .map(genderMapper::toDto)
                .collect(Collectors.toList());
    }
}
