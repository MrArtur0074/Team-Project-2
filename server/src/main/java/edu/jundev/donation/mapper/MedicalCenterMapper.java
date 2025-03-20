package edu.jundev.donation.mapper;

import edu.jundev.donation.dto.MedicalCenterDto;
import edu.jundev.donation.dto.requests.MedicalCenterRequest;
import edu.jundev.donation.entity.MedicalCenter;
import edu.jundev.donation.exception.NotFoundException;
import edu.jundev.donation.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class MedicalCenterMapper {
    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;


    public MedicalCenterDto toDto(MedicalCenter medicalCenter) {
        return MedicalCenterDto.builder()
                .id(medicalCenter.getId())
                .region(regionMapper.toDto(medicalCenter.getRegion()))
                .name(medicalCenter.getName())
                .location(medicalCenter.getLocation())
                .createdAt(medicalCenter.getCreatedAt())
                .updatedAt(medicalCenter.getUpdatedAt())
                .build();
    }

    public MedicalCenter toMedicalCentre(MedicalCenterRequest form) {
        return MedicalCenter.builder()
                .createdAt(LocalDateTime.now())
                .region(regionRepository.findById(form.getRegionId())
                        .orElseThrow(() -> new NotFoundException("Region with id " + form.getRegionId() + " not found!")))
                .name(form.getName())
                .location(form.getLocation())
                .build();
    }
}
