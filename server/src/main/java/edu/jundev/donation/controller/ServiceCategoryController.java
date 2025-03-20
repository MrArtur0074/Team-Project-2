package edu.jundev.donation.controller;

import edu.jundev.donation.dto.ServiceCategoryDto;
import edu.jundev.donation.dto.requests.ServiceCategoryRequest;
import edu.jundev.donation.service.CategoryService;
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
public class ServiceCategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ServiceCategoryDto> createCategory(@Valid @ModelAttribute ServiceCategoryRequest serviceCategoryRequest){
        return ResponseEntity.ok(categoryService.addCategory(serviceCategoryRequest));
    }

    @GetMapping
    public ResponseEntity<Page<ServiceCategoryDto>> findAll(@PageableDefault(size = 6, page = 0) Pageable pageable) {
        return ResponseEntity.ok(categoryService.findAll(pageable));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable(name = "id") Long id){
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok("Category has been successfully deleted");
    }
}
