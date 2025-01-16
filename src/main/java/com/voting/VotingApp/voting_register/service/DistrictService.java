package com.voting.VotingApp.voting_register.service;

import com.voting.VotingApp.voting_register.dto.DistrictDTO;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.entity.District;

public interface DistrictService {
    Response getAllDistricts();
    Response createDistrict(DistrictDTO districtDTO);
    Response getDistrictById(String districtId);
    Response updateDistrict(String districtId, DistrictDTO districtDTO);
    Response deleteDistrict(String districtId);

    Response getConstituenciesByDistrict(String districtId);
    Response deleteAllDistricts();
}
