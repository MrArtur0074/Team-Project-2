package edu.jundev.donation.controller;

import edu.jundev.donation.dto.ServiceCategoryDto;
import edu.jundev.donation.dto.requests.ServiceCategoryRequest;
import edu.jundev.donation.service.CategotyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class ServiceCategoryController {
    private final CategotyService categotyService;

    @PostMapping("/create")
    public ResponseEntity<ServiceCategoryDto> createCategory(@Valid @ModelAttribute ServiceCategoryRequest serviceCategoryRequest){
        return ResponseEntity.ok(categotyService.addCategory(serviceCategoryRequest));
    }

    @GetMapping
    public ResponseEntity<Page<ServiceCategoryDto>> findAll(@PageableDefault(size = 6, page = 0) Pageable pageable) {
        return ResponseEntity.ok(categotyService.findAll(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable(name = "id") Long id){
        categotyService.deleteCategoryById(id);
        return ResponseEntity.ok("Category has been successfully deleted");
    }
}
