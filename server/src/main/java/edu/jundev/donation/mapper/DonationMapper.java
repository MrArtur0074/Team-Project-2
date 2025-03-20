package edu.jundev.donation.mapper;

import edu.jundev.donation.dto.DonationDto;
import edu.jundev.donation.entity.Donation;
import edu.jundev.donation.entity.MedicalCenter;
import edu.jundev.donation.entity.User;
import edu.jundev.donation.entity.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DonationMapper {
    private final BloodTypeMapper bloodTypeMapper;
    private final MedicalCenterMapper medicalCenterMapper;
    private final UserMapper userMapper;

    public Donation toEntity(User user, UserInfo userInfo, MedicalCenter medicalCenter, BigDecimal bloodVolume) {
        return Donation.builder()
                .user(user)
                .medicalCenter(medicalCenter)
                .bloodType(userInfo.getBloodType())
                .volume(bloodVolume)
                .build();
    }


    public DonationDto toDto(Donation savedDonation) {
        return DonationDto.builder()
                .id(savedDonation.getId())
                .user(userMapper.toDto(savedDonation.getUser()))
                .medicalCenter(medicalCenterMapper.toDto(savedDonation.getMedicalCenter()))
                .bloodType(bloodTypeMapper.toDto(savedDonation.getBloodType()))
                .volume(savedDonation.getVolume())
                .createdAt(savedDonation.getCreatedAt())
                .points(savedDonation.getPoints())
                .build();
    }
}
