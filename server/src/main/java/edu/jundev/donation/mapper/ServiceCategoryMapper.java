package edu.jundev.donation.mapper;

import edu.jundev.donation.dto.ServiceCategoryDto;
import edu.jundev.donation.dto.requests.ServiceCategoryRequest;
import edu.jundev.donation.entity.ServiceCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceCategoryMapper {
    public ServiceCategory toEntity(ServiceCategoryRequest serviceCategoryRequest,String link){
        return ServiceCategory.builder()
                .imageUrl(link)
                .nameOfCategory(serviceCategoryRequest.getNameOfCategory())
                .price(serviceCategoryRequest.getPrice())
                .build();
    }

    public ServiceCategoryDto toDto(ServiceCategory serviceCategory){
        return ServiceCategoryDto.builder()
                .id(serviceCategory.getId())
                .nameOfCategory(serviceCategory.getNameOfCategory())
                .price(serviceCategory.getPrice())
                .imageUrl(serviceCategory.getImageUrl())
                .build();
    }
}
