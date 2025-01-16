package com.voting.VotingApp.voting_register.service.impl;

import com.voting.VotingApp.voting_register.dto.ConstituencyDTO;
import com.voting.VotingApp.voting_register.dto.ParliamentaryCandidateDTO;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.entity.Constituency;
import com.voting.VotingApp.voting_register.entity.District;
import com.voting.VotingApp.voting_register.entity.ParliamentaryCandidate;
import com.voting.VotingApp.voting_register.mapper.EntityDTOMapper;
import com.voting.VotingApp.voting_register.repository.ConstituencyRepository;
import com.voting.VotingApp.voting_register.repository.DistrictRepository;
import com.voting.VotingApp.voting_register.repository.ParliamentaryCandidateRepository;
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
    private final ParliamentaryCandidateRepository parliamentaryCandidateRepository;

    public ConstituencyServiceImp(ConstituencyRepository constituencyRepository, DistrictRepository districtRepository, EntityDTOMapper entityDTOMapper, ParliamentaryCandidateRepository parliamentaryCandidateRepository) {
        this.constituencyRepository = constituencyRepository;
        this.districtRepository = districtRepository;
        this.entityDTOMapper = entityDTOMapper;
        this.parliamentaryCandidateRepository = parliamentaryCandidateRepository;
    }

    @Override
    public Response createConstituency(ConstituencyDTO constituencyDTO) {

        System.out.println(constituencyDTO);

        Constituency newConstituency = new Constituency();
        newConstituency.setConstituencyName(constituencyDTO.getConstituencyName());
        newConstituency.setConstituencyCapital(constituencyDTO.getConstituencyCapital());
        newConstituency.setConstituencyElectoralCode(constituencyDTO.getConstituencyElectoralCode());

//        District district = districtRepository.findById(Long.valueOf(constituencyDTO.getDistrictElectoralCode())) //this wont work
        District district = districtRepository.findByDistrictElectoralCode(constituencyDTO.getDistrictElectoralCode())
                .orElseThrow(()-> new RuntimeException("District does not exist"));

        newConstituency.setDistrict(district); //we need the district, get it from the code

        String districtElectoralCode = district.getDistrictElectoralCode();
//        String regionElectoralCode = district.getRegion().getRegionElectoralCode();
        newConstituency.setConstituencyElectoralCode(districtElectoralCode + constituencyDTO.getConstituencyElectoralCode());

        constituencyRepository.save(newConstituency);

//        List<District> listOfDistricts = districtRepository.findAll();
//        listOfDistricts.forEach(
//                district1 -> System.out.println(district1.getDistrictElectoralCode())
//        );

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

        List<ConstituencyDTO> constituencyDTO = constituencies.stream().map(entityDTOMapper::mapConstituencyToConstituencyDTO).collect(Collectors.toList());

        return Response.builder()
                .constituencyDTOList(constituencyDTO)
                .message(message)
                .build();
    }


    @Override
    public Response getConstituencyById(String constituencyId) {
        Constituency constituency = constituencyRepository.findConstituencyByConstituencyElectoralCode(constituencyId).orElseThrow(()-> new RuntimeException("Constituency not found!"));

        return Response.builder()
                .message("District retrieved successfully!")
                .constituencyDTO(entityDTOMapper.mapConstituencyToConstituencyDTO(constituency))
                .build();
    }

    @Override
    public Response updateConstituency(String constituencyId, ConstituencyDTO constituencyDTO) {
        Constituency constituency = constituencyRepository.findConstituencyByConstituencyElectoralCode(constituencyId).orElseThrow(()-> new RuntimeException("Constituency not found!"));


        if (constituencyDTO.getConstituencyCapital() != null ) constituency.setConstituencyCapital(constituencyDTO.getConstituencyCapital());
//        if (constituencyDTO.getConstituencyElectoralCode() != null ) constituency.setConstituencyElectoralCode(constituencyDTO.getConstituencyElectoralCode());
        if (constituencyDTO.getConstituencyName() != null ) constituency.setConstituencyName(constituencyDTO.getConstituencyName());

        District district = districtRepository.findByDistrictElectoralCode(constituencyDTO.getDistrictElectoralCode()).orElseThrow(()-> new RuntimeException("District not found!"));
        if (constituencyDTO.getDistrictElectoralCode() != null ) constituency.setDistrict(district);

        constituencyRepository.save(constituency);

        return Response.builder()
                .message("District retrieved successfully!")
                .constituencyDTO(entityDTOMapper.mapConstituencyToConstituencyDTO(constituency))
                .build();
    }

    @Override
    public Response deleteConstituency(String constituencyId) {
        Optional<Constituency> optionalConstituency = constituencyRepository.findConstituencyByConstituencyElectoralCode(constituencyId);

        Constituency constituency;
        if (optionalConstituency.isPresent()) {
            constituency = optionalConstituency.get();
            constituencyRepository.delete(constituency);
        }

        return Response.builder()
                .message("Constituency successfully deleted")
                .build();
    }

    @Override
    public Response getParliamentaryCandidatesByConstituency(String constituencyId) {
        Constituency constituency = constituencyRepository.findConstituencyByConstituencyElectoralCode(constituencyId).orElseThrow(()-> new RuntimeException("Constituency does not exist"));

        List<ParliamentaryCandidate> parliamentaryCandidates = parliamentaryCandidateRepository.findParliamentaryCandidateByConstituency(constituency);

        List<ParliamentaryCandidateDTO> parliamentaryCandidateDTOS = parliamentaryCandidates.stream().map(entityDTOMapper::parliamentaryCandidateToParliamentaryCandidateDTO).collect(Collectors.toList());

        return Response.builder().parliamentaryCandidateDTOList(parliamentaryCandidateDTOS).build();
    }

    @Override
    public Response deleteAllConstituencies(){
        constituencyRepository.deleteAll();
        return Response.builder().message("deleted successful").build();

    }
}
