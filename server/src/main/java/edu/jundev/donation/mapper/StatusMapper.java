package edu.jundev.donation.mapper;

import edu.jundev.donation.dto.StatusDto;
import edu.jundev.donation.entity.Status;
import org.springframework.stereotype.Component;

@Component
public class StatusMapper {
    public Status initialStatus(){
        return Status.builder()
                .points(0)
                .queueNumber(0)
                .name("Новый")
                .build();

    }

    public StatusDto toDto(Status status) {
        return StatusDto.builder()
                .id(status.getId())
                .name(status.getName())
                .points(status.getPoints())
                .queueNumber(status.getQueueNumber())
                .build();
    }
}
