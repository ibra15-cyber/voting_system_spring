package com.voting.VotingApp.voting_register.service;

import com.voting.VotingApp.voting_register.dto.ParliamentaryCandidateDTO;
import com.voting.VotingApp.voting_register.dto.PresidentialCandidateDTO;
import com.voting.VotingApp.voting_register.dto.Response;

public interface PresidentialCandidateService {
    Response createPrCandidate(PresidentialCandidateDTO presidentialCandidateDTO);

    Response getAllPrCandidates();

    Response getPrCandidateById(Long candidateId);

    Response updatePrCandidate(Long candidateId, PresidentialCandidateDTO presidentialCandidateDTO);

    Response deletePrCandidate(Long candidateId);

    Response deleteAllPresidentialCandidates();
}
