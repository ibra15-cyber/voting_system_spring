package com.voting.VotingApp.voting_register.controller;

import com.voting.VotingApp.voting_register.dto.PollingStationDTO;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.service.PollingStationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/polling-station")
public class PollingStationController {

    private final PollingStationService pollingStationService;

    public PollingStationController(PollingStationService pollingStationService) {
        this.pollingStationService = pollingStationService;
    }


    @PostMapping
    public ResponseEntity<Response> createPollingStation(@RequestBody PollingStationDTO pollingStationDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(pollingStationService.createPollingStation(pollingStationDTO));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Response> getAllPollingStations() {
        return ResponseEntity.status(HttpStatus.OK).body(pollingStationService.getAllPollingStations());
    }

    @GetMapping("/{pollingStationId}")
    public ResponseEntity<Response> getPollingStationById(@PathVariable("pollingStationId") String pollingStationId) {
        return ResponseEntity.status(HttpStatus.OK).body(pollingStationService.getPollingStationById(pollingStationId));
    }


    @PutMapping("/{pollingStationId}")
    public ResponseEntity<Response> updatePollingStation(@PathVariable("pollingStationId") String pollingStationId, @RequestBody PollingStationDTO pollingStationDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(pollingStationService.updatePollingStation(pollingStationId, pollingStationDTO));
    }

    @DeleteMapping("/{pollingStationId}")
    public ResponseEntity<Response> deletePollingStation(@PathVariable("pollingStationId") String pollingStationId) {
        return ResponseEntity.status(HttpStatus.OK).body(pollingStationService.deletePollingStation(pollingStationId));
    }

}
