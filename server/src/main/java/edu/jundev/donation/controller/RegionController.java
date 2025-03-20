package edu.jundev.donation.controller;

import edu.jundev.donation.dto.RegionDto;
import edu.jundev.donation.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @GetMapping
    public ResponseEntity<List<RegionDto>> findAll() {
        return ResponseEntity.ok(regionService.findAll());
    }
}
