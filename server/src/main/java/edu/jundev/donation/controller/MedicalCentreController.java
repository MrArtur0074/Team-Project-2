package edu.jundev.donation.controller;

import edu.jundev.donation.dto.MedicalCenterDto;
import edu.jundev.donation.dto.requests.MedicalCentreRequest;
import edu.jundev.donation.service.MedicalCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/medical-centres")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class MedicalCentreController {
    private final MedicalCenterService medicalCenterService;
    @PostMapping("/add")
    public ResponseEntity<MedicalCenterDto> addMedicalCentre(@Valid @RequestBody MedicalCentreRequest medicalCentreRequest){
        return ResponseEntity.ok(medicalCenterService.addCentre(medicalCentreRequest));
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
}
