package com.voting.VotingApp.voting.controller;

import com.voting.VotingApp.voting.dto.RegionDTO;
import com.voting.VotingApp.voting.dto.Response;
import com.voting.VotingApp.voting.dto.VoterDTO;
import com.voting.VotingApp.voting.service.RegionService;
import com.voting.VotingApp.voting.service.VoterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/region")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }



    @PostMapping
    public ResponseEntity<Response> createRegion(@RequestBody RegionDTO regionDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(regionService.createRegion(regionDTO));
    }

//
//    @GetMapping("/get-all")
//    public ResponseEntity<Response> getAllVoters() {
//        return ResponseEntity.status(HttpStatus.OK).body(regionService.getAllVoters());
//    }

}
