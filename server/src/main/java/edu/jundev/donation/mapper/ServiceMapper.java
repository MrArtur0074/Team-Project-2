package edu.jundev.donation.mapper;

import edu.jundev.donation.dto.ServiceDto;
import edu.jundev.donation.dto.requests.ServiceRequest;
import edu.jundev.donation.entity.Services;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceMapper {
    public Services toEntity(ServiceRequest serviceRequest){
        return Services.builder()
                .price(serviceRequest.getPrice())
                .name(serviceRequest.getName())
                .medicalCenter(serviceRequest.getMedicalCenter())
                .serviceCategory(serviceRequest.getCategories())
                .build();
    }

    public ServiceDto toDto(Services services){
        return ServiceDto.builder()
                .id(services.getId())
                .name(services.getName())
                .price(services.getPrice())
                .medicalCenter(services.getMedicalCenter())
                .serviceCategory(services.getServiceCategory())
                .build();
    }
}
