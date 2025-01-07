package com.voting.VotingApp.voting.mapper;

import com.voting.VotingApp.voting.dto.DistrictDTO;
import com.voting.VotingApp.voting.dto.RegionDTO;
import com.voting.VotingApp.voting.entity.District;
import com.voting.VotingApp.voting.entity.Region;
import org.springframework.stereotype.Component;

@Component
public class EntityDTOMapper {

    public RegionDTO mapRegionToRegionDTO(Region region) {
        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setRegionName(region.getRegionName());
        regionDTO.setRegionId(region.getRegionId());

        return regionDTO;
    }

    public DistrictDTO mapDistrictToDistrictDTO(District district){

        DistrictDTO districtDTO = new DistrictDTO();

        districtDTO.setDistrictName(district.getDistrictName());
        districtDTO.setRegionId(district.getRegion().getRegionId());

        return districtDTO;
    }
}
