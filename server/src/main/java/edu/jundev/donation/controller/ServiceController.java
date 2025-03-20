package edu.jundev.donation.controller;

import edu.jundev.donation.dto.ServiceCategoryDto;
import edu.jundev.donation.dto.ServiceDto;
import edu.jundev.donation.dto.requests.ServiceRequest;
import edu.jundev.donation.service.CategoryService;
import edu.jundev.donation.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/service")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;
    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ServiceDto> createService(@Valid @RequestBody ServiceRequest serviceRequest){
        return ResponseEntity.ok(serviceService.addService(serviceRequest));
    }

    @GetMapping
    public ResponseEntity<List<ServiceDto>> findAll() {
        return ResponseEntity.ok(serviceService.findAll());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable(name = "id") Long id){
        serviceService.deleteServiceById(id);
        return ResponseEntity.ok("Service has been successfully deleted");
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ServiceDto>> findServiceByCategory(@PathVariable(name = "id") Long id){
        ServiceCategoryDto serviceCategory = categoryService.getCategoryById(id);
        return ResponseEntity.ok(serviceService.getServiceByCategory(serviceCategory));
    }
}
