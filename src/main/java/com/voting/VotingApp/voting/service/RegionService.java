package com.voting.VotingApp.voting.service;

import com.voting.VotingApp.voting.dto.RegionDTO;
import com.voting.VotingApp.voting.dto.Response;
import com.voting.VotingApp.voting.dto.VoterDTO;

public interface RegionService {
    public Response getAllRegions();
    public Response createRegion(RegionDTO regionDTO);
}
