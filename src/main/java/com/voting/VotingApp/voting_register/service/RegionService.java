package com.voting.VotingApp.voting_register.service;

import com.voting.VotingApp.voting_register.dto.RegionDTO;
import com.voting.VotingApp.voting_register.dto.Response;

public interface RegionService {
    Response getAllRegions();
    Response createRegion(RegionDTO regionDTO);
    Response getRegionById(String regionCode);
    Response updateRegion(String regionCode, RegionDTO regionDTO);
    Response deleteRegion(String regionCode);

    Response getDistrictsByRegion(String regionId);
    Response deleteAllRegions();

}
