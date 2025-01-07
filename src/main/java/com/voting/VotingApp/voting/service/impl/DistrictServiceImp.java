package com.voting.VotingApp.voting.service.impl;

import com.voting.VotingApp.voting.dto.DistrictDTO;
import com.voting.VotingApp.voting.dto.RegionDTO;
import com.voting.VotingApp.voting.dto.Response;
import com.voting.VotingApp.voting.entity.District;
import com.voting.VotingApp.voting.entity.Region;
import com.voting.VotingApp.voting.mapper.EntityDTOMapper;
import com.voting.VotingApp.voting.repository.DistrictRepository;
import com.voting.VotingApp.voting.repository.RegionRepository;
import com.voting.VotingApp.voting.service.DistrictService;
import com.voting.VotingApp.voting.service.RegionService;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImp implements DistrictService {

    private final DistrictRepository districtRepository;
    private final EntityDTOMapper entityDTOMapper;
    private final RegionRepository regionRepository;

    public DistrictServiceImp(DistrictRepository districtRepository, EntityDTOMapper entityDTOMapper, RegionRepository regionRepository) {
        this.districtRepository = districtRepository;
        this.entityDTOMapper = entityDTOMapper;
        this.regionRepository = regionRepository;
    }



    @Override
    public Response getAllDistricts() {
        return null;
    }

    @Override
    public Response createDistrict(DistrictDTO districtDTO) {
        District district = new District();
        district.setDistrictName(districtDTO.getDistrictName());

        Region region = regionRepository.findById(districtDTO.getRegionId()).orElseThrow(()-> new RuntimeException("Region not found"));
        district.setRegion(region);

        districtRepository.save(district);

        DistrictDTO districtDTO1 = entityDTOMapper.mapDistrictToDistrictDTO(district);

       return Response.builder()
                .message("District created successfully")
                .districtDTO(districtDTO1)
                .build();
    }
}
