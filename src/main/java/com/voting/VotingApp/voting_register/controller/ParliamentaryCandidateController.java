package com.voting.VotingApp.voting_register.controller;

import com.voting.VotingApp.voting_register.dto.ParliamentaryCandidateDTO;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.service.ParliamentaryCandidateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate")
public class ParliamentaryCandidateController {

    private final ParliamentaryCandidateService parliamentaryCandidateService;

    public ParliamentaryCandidateController(ParliamentaryCandidateService parliamentaryCandidateService) {
        this.parliamentaryCandidateService = parliamentaryCandidateService;
    }



    @PostMapping
    public ResponseEntity<Response> createCandidate(@RequestBody ParliamentaryCandidateDTO parliamentaryCandidateDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(parliamentaryCandidateService.createCandidate(parliamentaryCandidateDTO));
    }


    @GetMapping("/get-all")
    public ResponseEntity<Response> getAllCandidates() {
        return ResponseEntity.status(HttpStatus.OK).body(parliamentaryCandidateService.getAllCandidates());
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<Response> getCandidateById(@PathVariable("candidateId") Long candidateId) {
        return ResponseEntity.status(HttpStatus.OK).body(parliamentaryCandidateService.getCandidateById(candidateId));
    }


    @PutMapping("/{candidateId}")
    public ResponseEntity<Response> updateVoter(@PathVariable("candidateId") Long candidateId, @RequestBody ParliamentaryCandidateDTO parliamentaryCandidateDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(parliamentaryCandidateService.updateCandidate(candidateId, parliamentaryCandidateDTO));
    }

    @DeleteMapping("/{candidateId}")
    public ResponseEntity<Response> deleteVoter(@PathVariable("candidateId") Long candidateId) {
        return ResponseEntity.status(HttpStatus.OK).body(parliamentaryCandidateService.deleteCandidate(candidateId));
    }

}
