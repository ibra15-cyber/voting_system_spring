package com.voting.VotingApp.voting.controller;

import com.voting.VotingApp.voting.dto.DistrictDTO;
import com.voting.VotingApp.voting.dto.RegionDTO;
import com.voting.VotingApp.voting.dto.Response;
import com.voting.VotingApp.voting.service.DistrictService;
import com.voting.VotingApp.voting.service.RegionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/district")
public class DistrictController {

    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }



    @PostMapping
    public ResponseEntity<Response> createDistrict(@RequestBody DistrictDTO districtDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(districtService.createDistrict(districtDTO));
    }

//
//    @GetMapping("/get-all")
//    public ResponseEntity<Response> getAllVoters() {
//        return ResponseEntity.status(HttpStatus.OK).body(regionService.getAllVoters());
//    }

}
