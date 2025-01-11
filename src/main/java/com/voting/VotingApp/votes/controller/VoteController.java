package com.voting.VotingApp.votes.controller;

import com.voting.VotingApp.votes.dto.VoteDTO;
import com.voting.VotingApp.votes.entity.Vote;
import com.voting.VotingApp.votes.service.VoteService;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.service.VoterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }


    @PostMapping
    public ResponseEntity<Response> vote (@RequestBody VoteDTO voteDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(voteService.vote(voteDTO));
    }

    @GetMapping("/presidential-summary")
    public ResponseEntity<Response> getPresidentialVoteSummaryByConstituency () {
        return ResponseEntity.status(HttpStatus.OK).body(voteService.getPresidentialVoteSummaryByConstituency());
    }

    @GetMapping("/parliamentary-summary")
    public ResponseEntity<Response> getParliamentaryVoteSummary () {
        return ResponseEntity.status(HttpStatus.OK).body(voteService.getParliamentaryVoteSummary());
    }


    @GetMapping("/presidential-summary-by-district")
    public ResponseEntity<Response> getPresidentialVoteSummaryByDistrict() {
        return ResponseEntity.status(HttpStatus.OK).body(voteService.getPresidentialVoteSummaryByDistrict());
    }
}
