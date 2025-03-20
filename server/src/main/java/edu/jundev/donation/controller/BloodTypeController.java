package edu.jundev.donation.controller;

import edu.jundev.donation.dto.BloodTypeDto;
import edu.jundev.donation.service.BloodTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blood/types")
@RequiredArgsConstructor
public class BloodTypeController {
    private final BloodTypeService bloodTypeService;

    @GetMapping
    public ResponseEntity<List<BloodTypeDto>> findAll() {
        return ResponseEntity.ok(bloodTypeService.findAll());
    }
}
