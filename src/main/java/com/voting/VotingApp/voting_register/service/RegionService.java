package com.voting.VotingApp.voting_register.service;

import com.voting.VotingApp.voting_register.dto.RegionDTO;
import com.voting.VotingApp.voting_register.dto.Response;

public interface RegionService {
    Response getAllRegions();
    Response createRegion(RegionDTO regionDTO);
    Response getRegionById(Long regionId);
    Response updateRegion(Long regionId, RegionDTO regionDTO);
    Response deleteRegion(Long regionId);

    Response getDistrictsByRegion(Long regionId);
}
