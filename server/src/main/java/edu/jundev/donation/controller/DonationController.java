package edu.jundev.donation.controller;

import edu.jundev.donation.dto.DonationDto;
import edu.jundev.donation.dto.requests.DonationRequest;
import edu.jundev.donation.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/donations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class DonationController {
    private final DonationService donationService;

    @PostMapping
    public ResponseEntity<DonationDto> donate(@Valid @RequestBody DonationRequest form) {
        return ResponseEntity.ok(donationService.donate(form));
    }
}
