package com.voting.VotingApp.voting.service;

import com.voting.VotingApp.voting.dto.VoterDTO;
import com.voting.VotingApp.voting.entity.Voter;
import com.voting.VotingApp.voting.dto.Response;

public interface VoterService {
    public Response getAllVoters();
    public Response createVoter(VoterDTO voterDTO);
}
