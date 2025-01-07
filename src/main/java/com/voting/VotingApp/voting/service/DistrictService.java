package com.voting.VotingApp.voting.service;

import com.voting.VotingApp.voting.dto.DistrictDTO;
import com.voting.VotingApp.voting.dto.RegionDTO;
import com.voting.VotingApp.voting.dto.Response;

public interface DistrictService {
    public Response getAllDistricts();
    public Response createDistrict(DistrictDTO districtDTO);
}
