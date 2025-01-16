package com.voting.VotingApp.voting_register.service;

import com.voting.VotingApp.voting_register.dto.ConstituencyDTO;
import com.voting.VotingApp.voting_register.dto.Response;

public interface ConstituencyService {
    Response createConstituency(ConstituencyDTO constituencyDTO);
    Response getAllConstituencies();
    Response getConstituencyById(String constituencyId);
    Response updateConstituency(String constituencyId, ConstituencyDTO constituencyDTO);
    Response deleteConstituency(String constituencyId);

    Response getParliamentaryCandidatesByConstituency(String constituencyId);

    Response deleteAllConstituencies();

}
