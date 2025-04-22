package edu.jundev.donation.service;

import edu.jundev.donation.dto.ServiceCategoryDto;
import edu.jundev.donation.dto.ServiceDto;
import edu.jundev.donation.dto.requests.ServiceRequest;
import edu.jundev.donation.entity.Services;
import edu.jundev.donation.exception.NotFoundException;
import edu.jundev.donation.mapper.ServiceMapper;
import edu.jundev.donation.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public ServiceDto addService(ServiceRequest serviceRequest){
        Services services = serviceMapper.toEntity(serviceRequest);
        Services saved = serviceRepository.save(services);
        return serviceMapper.toDto(saved);
    }

    public List<ServiceDto> findAll() {
        var services = serviceRepository.findAll();
        return services.stream()
                .map(serviceMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteServiceById(Long id) throws NotFoundException {
        Services services = serviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Service with id " + id + " not found!"));
        serviceRepository.delete(services);
    }

    public List<ServiceDto> getServiceByCategory(ServiceCategoryDto serviceCategory){
        var service = serviceRepository.findAll();
        return service.stream()
                .filter(i->i.getServiceCategory().equals(serviceCategory))
                .map(serviceMapper::toDto).collect(Collectors.toList());
    }
}
