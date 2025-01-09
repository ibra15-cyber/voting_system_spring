package com.voting.VotingApp.voting_register.service;

import com.voting.VotingApp.voting_register.dto.ConstituencyDTO;
import com.voting.VotingApp.voting_register.dto.Response;

public interface ConstituencyService {
    Response createConstituency(ConstituencyDTO constituencyDTO);
    Response getAllConstituencies();
    Response getConstituencyById(Long constituencyId);
    Response updateConstituency(Long constituencyId, ConstituencyDTO constituencyDTO);
    Response deleteConstituency(Long constituencyId);

    Response getParliamentaryCandidatesByConstituency(Long constituencyId);
}
