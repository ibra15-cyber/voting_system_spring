package com.voting.VotingApp.voting.controller;

import com.voting.VotingApp.voting.dto.ConstituencyDTO;
import com.voting.VotingApp.voting.dto.Response;
import com.voting.VotingApp.voting.dto.VoterDTO;
import com.voting.VotingApp.voting.entity.Constituency;
import com.voting.VotingApp.voting.service.ConstituencyService;
import com.voting.VotingApp.voting.service.VoterService;
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


//    @GetMapping("/get-all")
//    public ResponseEntity<Response> getAllConstituencies() {
//        return ResponseEntity.status(HttpStatus.OK).body(constituencyService.getAllConstituencies());
//    }

}
