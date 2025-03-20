package edu.jundev.donation.controller;

import edu.jundev.donation.dto.GenderDto;
import edu.jundev.donation.service.GenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genders")
@RequiredArgsConstructor
public class GenderController {
    private final GenderService genderService;

    @GetMapping
    public ResponseEntity<List<GenderDto>> findAll() {
        return ResponseEntity.ok(genderService.findAll());
    }
}
