package com.voting.VotingApp.voting.service.impl;

import com.voting.VotingApp.voting.dto.RegionDTO;
import com.voting.VotingApp.voting.dto.Response;
import com.voting.VotingApp.voting.entity.Region;
import com.voting.VotingApp.voting.mapper.EntityDTOMapper;
import com.voting.VotingApp.voting.repository.RegionRepository;
import com.voting.VotingApp.voting.service.RegionService;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImp implements RegionService {

    private final RegionRepository regionRepository;

    private final EntityDTOMapper entityDTOMapper;

    public RegionServiceImp(RegionRepository regionRepository, EntityDTOMapper entityDTOMapper) {
        this.regionRepository = regionRepository;
        this.entityDTOMapper = entityDTOMapper;
    }

    @Override
    public Response getAllRegions() {
        return null;
    }

    @Override
    public Response createRegion(RegionDTO regionDTO) {
        Region newRegion = new Region();

        newRegion.setRegionName(regionDTO.getRegionName());

        regionRepository.save(newRegion);

        RegionDTO regionDTO1 = entityDTOMapper.mapRegionToRegionDTO(newRegion);

        return Response.builder()
                .message("Region created successfully!")
                .regionDTO(regionDTO1)
                .build();
    }
}
