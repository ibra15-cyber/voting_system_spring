package com.voting.VotingApp.voting_register.service;

import com.voting.VotingApp.voting_register.dto.VoterDTO;
import com.voting.VotingApp.voting_register.dto.Response;

public interface VoterService {
    Response getAllVoters();
    Response createVoter(VoterDTO voterDTO);
    Response getVoterById(Long voterId);
    Response updateVoter(Long voterId, VoterDTO voterDTO);
    Response deleteVoter(Long voterId);

    Response deleteAllVoters();
}
