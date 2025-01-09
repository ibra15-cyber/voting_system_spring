package com.voting.VotingApp.voting_register.controller;

import com.voting.VotingApp.voting_register.dto.VoterDTO;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.repository.VoterRepository;
import com.voting.VotingApp.voting_register.service.VoterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voter")
public class VoterController {

    private final VoterService voterService;
    private final VoterRepository voterRepository;

    public VoterController(VoterService voterService, VoterRepository voterRepository) {
        this.voterService = voterService;
        this.voterRepository = voterRepository;
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

    @GetMapping("/{voterId}")
    public ResponseEntity<Response> getVoterById(@PathVariable("voterId") Long voterId) {
        return ResponseEntity.status(HttpStatus.OK).body(voterService.getVoterById(voterId));
    }


    @PutMapping("/{voterId}")
    public ResponseEntity<Response> updateVoter(@PathVariable("voterId") Long voterId, @RequestBody VoterDTO voterDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(voterService.updateVoter(voterId, voterDTO));
    }

    @DeleteMapping("/{voterId}")
    public ResponseEntity<Response> deleteVoter(@PathVariable("voterId") Long voterId) {
        return ResponseEntity.status(HttpStatus.OK).body(voterService.deleteVoter(voterId));
    }

}
