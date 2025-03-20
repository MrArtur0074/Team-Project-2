package edu.jundev.donation.mapper;

import edu.jundev.donation.dto.RegionDto;
import edu.jundev.donation.entity.Region;
import org.springframework.stereotype.Component;

@Component
public class RegionMapper {
    public RegionDto toDto(Region region) {
        return RegionDto.builder()
                .id(region.getId())
                .name(region.getName())
                .build();
    }

}
