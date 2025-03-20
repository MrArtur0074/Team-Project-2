package edu.jundev.donation.mapper;

import edu.jundev.donation.dto.GenderDto;
import edu.jundev.donation.entity.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenderMapper {

    public GenderDto toDto(Gender gender) {
        return edu.jundev.donation.dto.GenderDto.builder()
                .id(gender.getId())
                .name(gender.getName())
                .build();
    }
}
