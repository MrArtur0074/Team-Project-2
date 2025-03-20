package edu.jundev.donation.controller;

import edu.jundev.donation.dto.DonationDto;
import edu.jundev.donation.dto.requests.DonationRequest;
import edu.jundev.donation.entity.User;
import edu.jundev.donation.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/donations")
@RequiredArgsConstructor
public class DonationController {
    private final DonationService donationService;

    @PostMapping
    public ResponseEntity<DonationDto> donate(@Valid @RequestBody DonationRequest form) {
        return ResponseEntity.ok(donationService.donate(form));
    }

    @GetMapping("/history")
    public ResponseEntity<Page<DonationDto>> findHistory(@AuthenticationPrincipal User user,
                                                         @PageableDefault(size = 4, page = 0) Pageable pageable) {
        return ResponseEntity.ok(donationService.findHistory(user, pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<DonationDto>> findAll(@PageableDefault(size = 4) Pageable pageable) {
        return ResponseEntity.ok(donationService.findAll(pageable));
    }
}
