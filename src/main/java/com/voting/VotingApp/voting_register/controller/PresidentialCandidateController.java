package com.voting.VotingApp.voting_register.controller;

import com.voting.VotingApp.voting_register.dto.ParliamentaryCandidateDTO;
import com.voting.VotingApp.voting_register.dto.PresidentialCandidateDTO;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.service.ParliamentaryCandidateService;
import com.voting.VotingApp.voting_register.service.PresidentialCandidateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/presidential-candidate")
public class PresidentialCandidateController {

    private final PresidentialCandidateService presidentialCandidateService;

    public PresidentialCandidateController(PresidentialCandidateService presidentialCandidateService) {
        this.presidentialCandidateService = presidentialCandidateService;
    }



    @PostMapping
    public ResponseEntity<Response> createPrCandidate(@RequestBody PresidentialCandidateDTO presidentialCandidateDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(presidentialCandidateService.createPrCandidate(presidentialCandidateDTO));
    }


    @GetMapping("/get-all")
    public ResponseEntity<Response> getAllPrCandidates() {
        return ResponseEntity.status(HttpStatus.OK).body(presidentialCandidateService.getAllPrCandidates());
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<Response> getPrCandidateById(@PathVariable("candidateId") Long voterIdNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(presidentialCandidateService.getPrCandidateById(voterIdNumber));
    }


    @PutMapping("/{candidateId}")
    public ResponseEntity<Response> updatePrCandidate(@PathVariable("candidateId") Long candidateId, @RequestBody PresidentialCandidateDTO presidentialCandidateDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(presidentialCandidateService.updatePrCandidate(candidateId, presidentialCandidateDTO));
    }

    @DeleteMapping("/{candidateId}")
    public ResponseEntity<Response> deletePrCandidate(@PathVariable("candidateId") Long candidateId) {
        return ResponseEntity.status(HttpStatus.OK).body(presidentialCandidateService.deletePrCandidate(candidateId));
    }

}
