package edu.jundev.donation.service;

import edu.jundev.donation.dto.BloodTypeDto;
import edu.jundev.donation.mapper.BloodTypeMapper;
import edu.jundev.donation.repository.BloodTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BloodTypeService {
    private final BloodTypeRepository bloodTypeRepository;
    private final BloodTypeMapper bloodTypeMapper;

    public List<BloodTypeDto> findAll() {
        var bloodTypes = bloodTypeRepository.findAll();
        return bloodTypes.stream()
                .map(bloodTypeMapper::toDto)
                .collect(Collectors.toList());
    }
}
