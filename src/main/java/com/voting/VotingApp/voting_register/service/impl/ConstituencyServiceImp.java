package com.voting.VotingApp.voting_register.service.impl;

import com.voting.VotingApp.voting_register.dto.ConstituencyDTO;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.entity.Constituency;
import com.voting.VotingApp.voting_register.entity.District;
import com.voting.VotingApp.voting_register.mapper.EntityDTOMapper;
import com.voting.VotingApp.voting_register.repository.ConstituencyRepository;
import com.voting.VotingApp.voting_register.repository.DistrictRepository;
import com.voting.VotingApp.voting_register.service.ConstituencyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConstituencyServiceImp implements ConstituencyService {

    private final ConstituencyRepository constituencyRepository;
    private final DistrictRepository districtRepository;
    private final EntityDTOMapper entityDTOMapper;

    public ConstituencyServiceImp(ConstituencyRepository constituencyRepository, DistrictRepository districtRepository, EntityDTOMapper entityDTOMapper) {
        this.constituencyRepository = constituencyRepository;
        this.districtRepository = districtRepository;
        this.entityDTOMapper = entityDTOMapper;
    }

    @Override
    public Response createConstituency(ConstituencyDTO constituencyDTO) {

        System.out.println(constituencyDTO);

        Constituency newConstituency = new Constituency();
        newConstituency.setConstituencyName(constituencyDTO.getConstituencyName());
        newConstituency.setConstituencyCapital(constituencyDTO.getConstituencyCapital());
        newConstituency.setConstituencyElectoralCode(constituencyDTO.getConstituencyElectoralCode());

        District district = districtRepository.findById(constituencyDTO.getDistrictId())
                .orElseThrow(()-> new RuntimeException("District does not exist"));

        newConstituency.setDistrict(district);

        constituencyRepository.save(newConstituency);

        return Response.builder()
                .message("Constituency created successfully!")
                .build();
    }

    @Override
    public Response getAllConstituencies() {
        List<Constituency> constituencies = constituencyRepository.findAll();

        String message = "";
        if (constituencies.isEmpty()){
            message = "No Constituency found";
        }

        List<ConstituencyDTO> constituencyDTO = constituencies.stream().map(entityDTOMapper::mapConstituencyToContituencyDTO).collect(Collectors.toList());

        return Response.builder()
                .constituencyDTOList(constituencyDTO)
                .message(message)
                .build();
    }


    @Override
    public Response getConstituencyById(Long constituencyId) {
        Constituency constituency = constituencyRepository.findById(constituencyId).orElseThrow(()-> new RuntimeException("Constituency not found!"));

        return Response.builder()
                .message("District retrieved successfully!")
                .constituencyDTO(entityDTOMapper.mapConstituencyToContituencyDTO(constituency))
                .build();
    }

    @Override
    public Response updateConstituency(Long constituencyId, ConstituencyDTO constituencyDTO) {
        Constituency constituency = constituencyRepository.findById(constituencyId).orElseThrow(()-> new RuntimeException("Constituency not found!"));


        if (constituencyDTO.getConstituencyCapital() != null ) constituency.setConstituencyCapital(constituencyDTO.getConstituencyCapital());
        if (constituencyDTO.getConstituencyElectoralCode() != null ) constituency.setConstituencyElectoralCode(constituencyDTO.getConstituencyElectoralCode());
        if (constituencyDTO.getConstituencyName() != null ) constituency.setConstituencyName(constituencyDTO.getConstituencyName());

        District district = districtRepository.findById(constituencyDTO.getDistrictId()).orElseThrow(()-> new RuntimeException("District not found!"));
        if (constituencyDTO.getDistrictId() != null ) constituency.setDistrict(district);

        constituencyRepository.save(constituency);

        return Response.builder()
                .message("District retrieved successfully!")
                .constituencyDTO(entityDTOMapper.mapConstituencyToContituencyDTO(constituency))
                .build();
    }

    @Override
    public Response deleteConstituency(Long constituencyId) {
        Optional<Constituency> optionalConstituency = constituencyRepository.findById(constituencyId);

        Constituency constituency;
        if (optionalConstituency.isPresent()) {
            constituency = optionalConstituency.get();
            constituencyRepository.delete(constituency);
        }

        return Response.builder()
                .message("Constituency successfully deleted")
                .build();
    }
}
