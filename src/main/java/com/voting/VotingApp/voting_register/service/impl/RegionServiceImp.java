package com.voting.VotingApp.voting_register.service.impl;

import com.voting.VotingApp.voting_register.dto.RegionDTO;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.entity.Region;
import com.voting.VotingApp.voting_register.mapper.EntityDTOMapper;
import com.voting.VotingApp.voting_register.repository.RegionRepository;
import com.voting.VotingApp.voting_register.service.RegionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImp implements RegionService {

    private final RegionRepository regionRepository;

    private final EntityDTOMapper entityDTOMapper;

    public RegionServiceImp(RegionRepository regionRepository, EntityDTOMapper entityDTOMapper) {
        this.regionRepository = regionRepository;
        this.entityDTOMapper = entityDTOMapper;
    }


    @Override
    public Response createRegion(RegionDTO regionDTO) {
        Region newRegion = new Region();

        newRegion.setRegionName(regionDTO.getRegionName());
        newRegion.setRegionalCapital(regionDTO.getRegionalCapital());
        newRegion.setRegionElectoralCode(regionDTO.getRegionElectoralCode());

        regionRepository.save(newRegion);

        return Response.builder()
                .message("Region created successfully!")
                .regionDTO(entityDTOMapper.mapRegionToRegionDTO(newRegion))
                .build();
    }


    @Override
    public Response getAllRegions() {

        List<Region> regions = regionRepository.findAll();

        List<RegionDTO> regionDTOS = regions.stream().map(entityDTOMapper::mapRegionToRegionDTO).toList();

        return Response.builder()
                .message("Region created successfully!")
                .regionDTOList(regionDTOS)
                .build();
    }

    @Override
    public Response getRegionById(Long regionId) {

        Region region = regionRepository.findById(regionId)
                .orElseThrow(()-> new RuntimeException("region does not exist"));

        return Response.builder()
                .message("Region created successfully!")
                .regionDTO(entityDTOMapper.mapRegionToRegionDTO(region))
                .build();
    }

    @Override
    public Response updateRegion(Long regionId, RegionDTO regionDTO) {

        Region region = regionRepository.findById(regionId)
                .orElseThrow(()-> new RuntimeException("region does not exist"));

        if(regionDTO.getRegionalCapital() != null) region.setRegionalCapital(regionDTO.getRegionalCapital());
        if(regionDTO.getRegionalCapital() != null) region.setRegionName(regionDTO.getRegionName());
        if(regionDTO.getRegionElectoralCode() != null) region.setRegionElectoralCode(regionDTO.getRegionElectoralCode());

        regionRepository.save(region);

        return Response.builder()
                .message("Region updated successfully!")
                .regionDTO(entityDTOMapper.mapRegionToRegionDTO(region))
                .build();
    }


    @Override
    public Response deleteRegion(Long regionId) {

        Region region = regionRepository.findById(regionId)
                .orElseThrow(()-> new RuntimeException("region does not exist"));

        regionRepository.delete(region);
        return Response.builder()
                .message("Region deleted successfully!")
                .build();
    }




}
