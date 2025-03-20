package edu.jundev.donation.controller;

import edu.jundev.donation.dto.StatusDto;
import edu.jundev.donation.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statuses")
@RequiredArgsConstructor
public class StatusController {
    private final StatusService statusService;

    @GetMapping
    public ResponseEntity<List<StatusDto>> findAll() {
        return ResponseEntity.ok(statusService.findAll());
    }
}
