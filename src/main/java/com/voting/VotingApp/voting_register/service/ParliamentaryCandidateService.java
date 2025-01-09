package com.voting.VotingApp.voting_register.service;

import com.voting.VotingApp.voting_register.dto.ParliamentaryCandidateDTO;
import com.voting.VotingApp.voting_register.dto.Response;

public interface ParliamentaryCandidateService {
    Response getAllCandidates();
    Response createCandidate(ParliamentaryCandidateDTO parliamentaryCandidateDTO);
    Response getCandidateById(Long candidateId);
    Response updateCandidate(Long candidateId, ParliamentaryCandidateDTO parliamentaryCandidateDTO);
    Response deleteCandidate(Long candidateId);
}
