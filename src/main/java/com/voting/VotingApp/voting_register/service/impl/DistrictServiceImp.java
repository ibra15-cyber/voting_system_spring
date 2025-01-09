package com.voting.VotingApp.voting_register.service.impl;

import com.voting.VotingApp.voting_register.dto.ConstituencyDTO;
import com.voting.VotingApp.voting_register.dto.DistrictDTO;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.entity.Constituency;
import com.voting.VotingApp.voting_register.entity.District;
import com.voting.VotingApp.voting_register.entity.Region;
import com.voting.VotingApp.voting_register.mapper.EntityDTOMapper;
import com.voting.VotingApp.voting_register.repository.ConstituencyRepository;
import com.voting.VotingApp.voting_register.repository.DistrictRepository;
import com.voting.VotingApp.voting_register.repository.RegionRepository;
import com.voting.VotingApp.voting_register.service.DistrictService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DistrictServiceImp implements DistrictService {

    private final DistrictRepository districtRepository;
    private final EntityDTOMapper entityDTOMapper;
    private final RegionRepository regionRepository;
    private final ConstituencyRepository constituencyRepository;

    public DistrictServiceImp(DistrictRepository districtRepository, EntityDTOMapper entityDTOMapper, RegionRepository regionRepository, ConstituencyRepository constituencyRepository) {
        this.districtRepository = districtRepository;
        this.entityDTOMapper = entityDTOMapper;
        this.regionRepository = regionRepository;
        this.constituencyRepository = constituencyRepository;
    }


    @Override
    public Response createDistrict(DistrictDTO districtDTO) {
        District district = new District();
        district.setDistrictName(districtDTO.getDistrictName());
        district.setDistrictCapital(districtDTO.getDistrictCapital());
        district.setDistrictElectoralCode(districtDTO.getDistrictElectoralCode());

        Region region = regionRepository.findById(districtDTO.getRegionId()).orElseThrow(()-> new RuntimeException("Region not found"));
        district.setRegion(region);


        districtRepository.save(district);

        DistrictDTO districtDTO1 = entityDTOMapper.mapDistrictToDistrictDTO(district);


       return Response.builder()
                .message("District created successfully")
                .districtDTO(districtDTO1)
                .build();
    }

    @Override
    public Response getAllDistricts() {
        List<District> districts = districtRepository.findAll();

        String message = "";
        if (districts.isEmpty()){
            message = "No district found";
        }

        List<DistrictDTO> districtDTO = districts.stream().map(entityDTOMapper::mapDistrictToDistrictDTO).collect(Collectors.toList());

        return Response.builder()
                .districtDTOList(districtDTO)
                .message(message)
                .build();
    }


    @Override
    public Response getDistrictById(Long districtId) {

        District district = districtRepository.findById(districtId).orElseThrow(()-> new RuntimeException("District not found!"));


        return Response.builder()
                .message("District retrieved successfully!")
                .districtDTO(entityDTOMapper.mapDistrictToDistrictDTO(district))
                .build();
    }

    @Override
    public Response updateDistrict(Long districtId, DistrictDTO districtDTO) {
        District district = districtRepository.findById(districtId).orElseThrow(()-> new RuntimeException("District not found!"));


        if (districtDTO.getDistrictCapital() != null ) district.setDistrictCapital(districtDTO.getDistrictCapital());
        if (districtDTO.getDistrictElectoralCode() != null ) district.setDistrictElectoralCode(districtDTO.getDistrictElectoralCode());
        if (districtDTO.getDistrictName() != null ) district.setDistrictName(districtDTO.getDistrictName());
        if (districtDTO.getRegionId() != null ) district.setDistrictId(districtDTO.getRegionId());

        districtRepository.save(district);

        return Response.builder()
                .message("District retrieved successfully!")
                .districtDTO(entityDTOMapper.mapDistrictToDistrictDTO(district))
                .build();
    }

    @Override
    public Response deleteDistrict(Long districtId) {
        Optional<District> optionalDistrict = districtRepository.findById(districtId);

        District district;
        if (optionalDistrict.isPresent()) {
            district = optionalDistrict.get();
            districtRepository.delete(district);
        }

        return Response.builder()
                .message("District successfully deleted")
                .build();
    }

    @Override
    public Response getConstituenciesByDistrict(Long districtId) {
        District district = districtRepository.findById(districtId).orElseThrow(() -> new RuntimeException("District does not exist"));

        List<Constituency> constituencies = constituencyRepository.findConstituenciesByDistrict(district);

        List<ConstituencyDTO> constituencyDTOS = constituencies.stream().map(entityDTOMapper::mapConstituencyToContituencyDTO).collect(Collectors.toList());

        return Response.builder()
                .constituencyDTOList(constituencyDTOS)
                .build();
    }
}
