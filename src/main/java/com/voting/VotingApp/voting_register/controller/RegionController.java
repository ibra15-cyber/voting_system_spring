package com.voting.VotingApp.voting_register.controller;

import com.voting.VotingApp.voting_register.dto.RegionDTO;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.service.RegionService;
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


    @GetMapping("/get-all")
    public ResponseEntity<Response> getAllRegions() {
        return ResponseEntity.status(HttpStatus.OK).body(regionService.getAllRegions());
    }

    @GetMapping("/{regionId}")
    public ResponseEntity<Response> getRegionById(@PathVariable("regionId") Long regionId) {
        return ResponseEntity.status(HttpStatus.OK).body(regionService.getRegionById(regionId));
    }


    @PutMapping("/{regionId}")
    public ResponseEntity<Response> updateRegion(@PathVariable("regionId") Long regionId, @RequestBody RegionDTO regionDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(regionService.updateRegion(regionId, regionDTO));
    }

    @DeleteMapping("/{regionId}")
    public ResponseEntity<Response> deleteRegion(@PathVariable("regionId") Long regionId) {
        return ResponseEntity.status(HttpStatus.OK).body(regionService.deleteRegion(regionId));
    }


    @GetMapping("/get-districts-by-region/{regionId}")
    public ResponseEntity<Response> getDistrictsByRegion(@PathVariable("regionId") Long regionId) {
        return ResponseEntity.status(HttpStatus.OK).body(regionService.getDistrictsByRegion(regionId));
    }

}
