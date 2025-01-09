package com.voting.VotingApp.votes.service;

import com.voting.VotingApp.votes.dto.VoteDTO;
import com.voting.VotingApp.voting_register.dto.Response;

public interface VoteService {
    Response vote(VoteDTO voteDTO);
}
