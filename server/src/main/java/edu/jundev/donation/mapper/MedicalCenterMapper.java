package edu.jundev.donation.mapper;

import edu.jundev.donation.dto.MedicalCenterDto;
import edu.jundev.donation.dto.requests.MedicalCentreRequest;
import edu.jundev.donation.entity.MedicalCenter;
import edu.jundev.donation.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor

public class MedicalCenterMapper {
    private final RegionRepository regionRepository;
    public MedicalCenterDto toDto(MedicalCenter medicalCenter) {
        if (medicalCenter == null) return null;
        return MedicalCenterDto.builder()
                .id(medicalCenter.getId())
                .region(medicalCenter.getRegion())
                .name(medicalCenter.getName())
                .location(medicalCenter.getLocation())
                .createdAt(medicalCenter.getCreatedAt())
                .updatedAt(medicalCenter.getUpdatedAt())
                .build();
    }

    public MedicalCenter toMedicalCentre(MedicalCentreRequest medicalCentreRequest) {
        return MedicalCenter.builder()
                .createdAt(LocalDateTime.now())
                .region(regionRepository.findById(medicalCentreRequest.getRegionId()).orElseThrow())
                .name(medicalCentreRequest.getName())
                .location(medicalCentreRequest.getLocation())
                .build();
    }
}
