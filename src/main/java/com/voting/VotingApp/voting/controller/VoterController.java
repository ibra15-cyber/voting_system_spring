package com.voting.VotingApp.voting.controller;

import com.voting.VotingApp.voting.dto.VoterDTO;
import com.voting.VotingApp.voting.dto.Response;
import com.voting.VotingApp.voting.service.VoterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vote")
public class VoterController {

    private final VoterService voterService;

    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }


    @GetMapping
    public ResponseEntity<Response> vote() {

        String myVote = "I just voted";
        Response response = Response.builder()
                .message(myVote)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping
    public ResponseEntity<Response> createVoter(@RequestBody VoterDTO voterDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(voterService.createVoter(voterDTO));
    }


    @GetMapping("/get-all")
    public ResponseEntity<Response> getAllVoters() {
        return ResponseEntity.status(HttpStatus.OK).body(voterService.getAllVoters());
    }

}
