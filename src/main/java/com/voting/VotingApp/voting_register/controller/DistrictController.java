package com.voting.VotingApp.voting_register.controller;

import com.voting.VotingApp.voting_register.dto.DistrictDTO;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.repository.DistrictRepository;
import com.voting.VotingApp.voting_register.service.DistrictService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/district")
public class DistrictController {

    private final DistrictService districtService;

    public DistrictController(DistrictService districtService, DistrictRepository districtRepository) {
        this.districtService = districtService;
    }



    @PostMapping
    public ResponseEntity<Response> createDistrict(@RequestBody DistrictDTO districtDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(districtService.createDistrict(districtDTO));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Response> getAllDistricts() {
        return ResponseEntity.status(HttpStatus.OK).body(districtService.getAllDistricts());
    }

    @GetMapping("/{districtId}")
    public ResponseEntity<Response> getDistrictsById(@PathVariable("districtId") Long districtId) {
        return ResponseEntity.status(HttpStatus.OK).body(districtService.getDistrictById(districtId));
    }


    @PutMapping("/{districtId}")
    public ResponseEntity<Response> updateDistrictById(@PathVariable("districtId") Long districtId, @RequestBody DistrictDTO districtDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(districtService.updateDistrict(districtId, districtDTO));
    }

    @DeleteMapping("/{districtId}")
    public ResponseEntity<Response> deleteDistrict(@PathVariable("districtId") Long districtId) {
        return ResponseEntity.status(HttpStatus.OK).body(districtService.deleteDistrict(districtId));
    }

}
