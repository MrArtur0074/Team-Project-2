package edu.jundev.donation.controller;

import edu.jundev.donation.dto.MedicalCenterDto;
import edu.jundev.donation.dto.requests.MedicalCenterRequest;
import edu.jundev.donation.service.MedicalCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/medical-centers")
@RequiredArgsConstructor
public class MedicalCentreController {
    private final MedicalCenterService medicalCenterService;


    @PostMapping("/add")
    public ResponseEntity<MedicalCenterDto> addMedicalCenter(@Valid @RequestBody MedicalCenterRequest medicalCenterRequest){
        return ResponseEntity.ok(medicalCenterService.addCentre(medicalCenterRequest));
    }

    @DeleteMapping("/delete")
    ResponseEntity<?> deleteMedicalCenter(@RequestParam Long id){
        medicalCenterService.deleteCenter(id);
        return ResponseEntity.ok("You successfully deleted medical center");
    }

    @GetMapping("/get")
    ResponseEntity<List<MedicalCenterDto>> findAllMedicalCenter(){
        return ResponseEntity.ok(medicalCenterService.findAll());
    }

    @GetMapping
    public ResponseEntity<List<MedicalCenterDto>> findByCity(@RequestParam("region") Long regionId) {
        return ResponseEntity.ok(medicalCenterService.findByRegion(regionId));
    }
}
