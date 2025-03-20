package edu.jundev.donation.mapper;

import edu.jundev.donation.dto.BloodTypeDto;
import edu.jundev.donation.entity.BloodType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BloodTypeMapper {

    public BloodTypeDto toDto(BloodType bloodType) {
        return edu.jundev.donation.dto.BloodTypeDto.builder()
                .id(bloodType.getId())
                .name(bloodType.getName())
                .build();
    }
}
