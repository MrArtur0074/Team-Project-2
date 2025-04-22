package edu.jundev.donation.mapper;

import edu.jundev.donation.dto.DonationDto;
import edu.jundev.donation.entity.Donation;
import edu.jundev.donation.entity.MedicalCenter;
import edu.jundev.donation.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DonationMapper {
    private final BloodTypeMapper bloodTypeMapper;
    private final MedicalCenterMapper medicalCenterMapper;

    public Donation toEntity(User user, MedicalCenter medicalCenter, BigDecimal bloodVolume) {
        return Donation.builder()
                .user(user)
                .medicalCenter(medicalCenter)
                .volume(bloodVolume)
                .build();
    }


    public DonationDto toDto(Donation savedDonation) {
        return DonationDto.builder()
                .id(savedDonation.getId())
                .bloodTypeDto(bloodTypeMapper.toDto(savedDonation.getBloodType()))
                .medicalCenterDto(medicalCenterMapper.toDto(savedDonation.getMedicalCenter()))
                .build();
    }
}
