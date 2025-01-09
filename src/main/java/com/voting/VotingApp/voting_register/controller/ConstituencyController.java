package com.voting.VotingApp.voting_register.controller;

import com.voting.VotingApp.voting_register.dto.ConstituencyDTO;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.service.ConstituencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/constituency")
public class ConstituencyController {

    private final ConstituencyService constituencyService;

    public ConstituencyController(ConstituencyService constituencyService) {
        this.constituencyService = constituencyService;
    }


    @PostMapping
    public ResponseEntity<Response> createConstituency(@RequestBody ConstituencyDTO constituencyDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(constituencyService.createConstituency(constituencyDTO));
    }

    @GetMapping("/get-all")
    public ResponseEntity<Response> getAllConstituencies() {
        return ResponseEntity.status(HttpStatus.OK).body(constituencyService.getAllConstituencies());
    }

    @GetMapping("/{constituencyId}")
    public ResponseEntity<Response> getConstituencyById(@PathVariable("constituencyId") Long constituencyId) {
        return ResponseEntity.status(HttpStatus.OK).body(constituencyService.getConstituencyById(constituencyId));
    }


    @PutMapping("/{constituencyId}")
    public ResponseEntity<Response> updateConstituency(@PathVariable("constituencyId") Long constituencyId, @RequestBody ConstituencyDTO constituencyDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(constituencyService.updateConstituency(constituencyId, constituencyDTO));
    }

    @DeleteMapping("/{constituencyId}")
    public ResponseEntity<Response> deleteConstituency(@PathVariable("constituencyId") Long constituencyId) {
        return ResponseEntity.status(HttpStatus.OK).body(constituencyService.deleteConstituency(constituencyId));
    }
}
