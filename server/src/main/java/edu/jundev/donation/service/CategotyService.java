package edu.jundev.donation.service;

import edu.jundev.donation.dto.ServiceCategoryDto;
import edu.jundev.donation.dto.requests.ServiceCategoryRequest;
import edu.jundev.donation.entity.ServiceCategory;
import edu.jundev.donation.exception.FileException;
import edu.jundev.donation.exception.NotFoundException;
import edu.jundev.donation.mapper.ServiceCategoryMapper;
import edu.jundev.donation.repository.ServiceCategoryRepository;
import edu.jundev.donation.utils.CloudStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategotyService {
    private final ServiceCategoryRepository serviceCategoryRepository;
    private final CloudStorage cloudStorage;
    private final ServiceCategoryMapper serviceCategoryMapper;

    public ServiceCategoryDto addCategory(ServiceCategoryRequest serviceCategoryRequest){
        if(!cloudStorage.isImageFile(serviceCategoryRequest.getImageUrl()))
            throw new FileException("Not image file has been uploaded!");
        String file = cloudStorage.uploadFile(serviceCategoryRequest.getImageUrl());
        ServiceCategory serviceCategory = serviceCategoryMapper.toEntity(serviceCategoryRequest,file);
        ServiceCategory saved = serviceCategoryRepository.save(serviceCategory);
        return serviceCategoryMapper.toDto(saved);
    }

    public Page<ServiceCategoryDto> findAll(Pageable pageable) {
        var service = serviceCategoryRepository.findByOrderByPrice(pageable);
        return service.map(serviceCategoryMapper::toDto);
    }

    public void deleteCategoryById(Long id) throws NotFoundException {
        ServiceCategory serviceCategory = serviceCategoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category with id " + id + " not found!"));
        serviceCategoryRepository.delete(serviceCategory);
    }

    public ServiceCategoryDto getCategoryById(Long id) throws NotFoundException {
        ServiceCategory serviceCategory = serviceCategoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category with id " + id + " not found!"));
        return serviceCategoryMapper.toDto(serviceCategory);
    }

}
