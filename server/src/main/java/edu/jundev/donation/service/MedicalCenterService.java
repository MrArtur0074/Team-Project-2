package edu.jundev.donation.service;

import edu.jundev.donation.dto.MedicalCenterDto;
import edu.jundev.donation.dto.requests.MedicalCenterRequest;
import edu.jundev.donation.entity.MedicalCenter;
import edu.jundev.donation.entity.Region;
import edu.jundev.donation.exception.NotFoundException;
import edu.jundev.donation.mapper.MedicalCenterMapper;
import edu.jundev.donation.repository.MedicalCenterRepository;
import edu.jundev.donation.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MedicalCenterService {
    private final MedicalCenterRepository medicalCenterRepository;
    private final RegionRepository regionRepository;
    private final MedicalCenterMapper medicalCenterMapper;

    public MedicalCenterDto addCentre(MedicalCenterRequest medicalCenterRequest) {
        MedicalCenter medicalCenter = medicalCenterMapper.toMedicalCentre(medicalCenterRequest);
        MedicalCenter saveMedicalCenter = medicalCenterRepository.save(medicalCenter);
        return medicalCenterMapper.toDto(saveMedicalCenter);
    }

    public void deleteCenter(Long id) {
        MedicalCenter medicalCenter = medicalCenterRepository.findById(id).orElseThrow(()->
                new NotFoundException("The medical center with id " + id + " does not exist"));
        medicalCenterRepository.delete(medicalCenter);
    }

    public List<MedicalCenterDto> findAll() {
        List<MedicalCenter> medicalCenters = medicalCenterRepository.findAll();
        return  medicalCenters.stream().map(medicalCenterMapper::toDto).collect(Collectors.toList());
    }

    public List<MedicalCenterDto> findByRegion(Long regionId) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new NotFoundException("Region with id " + regionId + " not found!"));
        var medCenters = medicalCenterRepository.findByRegion(region);
        return medCenters.stream()
                .map(medicalCenterMapper::toDto)
                .collect(Collectors.toList());
    }
}
