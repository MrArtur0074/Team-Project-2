package edu.jundev.donation.mapper;

import edu.jundev.donation.dto.ServiceDto;
import edu.jundev.donation.dto.requests.ServiceRequest;
import edu.jundev.donation.entity.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceMapper {
    public Service toEntity(ServiceRequest serviceRequest){
        return Service.builder()
                .price(serviceRequest.getPrice())
                .name(serviceRequest.getName())
                .medicalCenter(serviceRequest.getMedicalCenter())
                .serviceCategory(serviceRequest.getCategories())
                .build();
    }

    public ServiceDto toDto(Service service){
        return ServiceDto.builder()
                .id(service.getId())
                .name(service.getName())
                .price(service.getPrice())
                .medicalCenter(service.getMedicalCenter())
                .serviceCategory(service.getServiceCategory())
                .build();
    }
}
