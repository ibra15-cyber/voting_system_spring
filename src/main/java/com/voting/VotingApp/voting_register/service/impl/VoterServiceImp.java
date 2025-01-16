package com.voting.VotingApp.voting_register.service.impl;

import com.voting.VotingApp.voting_register.dto.VoterDTO;
import com.voting.VotingApp.voting_register.entity.*;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.mapper.EntityDTOMapper;
import com.voting.VotingApp.voting_register.repository.ConstituencyRepository;
import com.voting.VotingApp.voting_register.repository.PollingStationRepository;
import com.voting.VotingApp.voting_register.repository.VoterRepository;
import com.voting.VotingApp.voting_register.service.VoterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoterServiceImp implements VoterService {

    private final VoterRepository voterRepository;
    private final ConstituencyRepository constituencyRepository;
    private final EntityDTOMapper entityDTOMapper;
    private final PollingStationRepository pollingStationRepository;

    public VoterServiceImp(VoterRepository voterRepository, ConstituencyRepository constituencyRepository, EntityDTOMapper entityDTOMapper, PollingStationRepository pollingStationRepository, PollingStationRepository pollingStationRepository1) {
        this.voterRepository = voterRepository;
        this.constituencyRepository = constituencyRepository;
        this.entityDTOMapper = entityDTOMapper;
        this.pollingStationRepository = pollingStationRepository1;
    }

    @Override
    public Response createVoter(VoterDTO voterDTO) {

        System.out.println(voterDTO);

        Voter newVoter = new Voter();
        System.out.println(voterDTO);

        newVoter.setAge(voterDTO.getAge());
        newVoter.setGender((voterDTO.getGender()));
        newVoter.setFirstName(voterDTO.getFirstName());
        newVoter.setLastName(voterDTO.getLastName());
        newVoter.setVoterNumber(voterDTO.getVoterNumber());

//        Constituency constituency = constituencyRepository.findConstituencyByConstituencyElectoralCode(voterDTO.getConstituencyElectoralCode()).orElseThrow( () -> new RuntimeException("Constituency does not exist"));
//        newVoter.setConstituency(constituency);

//        District district = constituency.getDistrict();
//        Region region = district.getRegion();

        PollingStation pollingStation = pollingStationRepository.findByPollingStationCode(voterDTO.getVoterPollingStationElectoralCode())
                .orElseThrow(()-> new RuntimeException("Polling Station not found"));
        newVoter.setPollingStation(pollingStation);

        voterRepository.save(newVoter);

        return Response.builder()
                .message("Voter created successfully!")
//                .regionDTO( entityDTOMapper.mapRegionToRegionDTO(region))
//                .districtDTO(entityDTOMapper.mapDistrictToDistrictDTO(district))
//                .constituencyDTO(entityDTOMapper.mapConstituencyToConstituencyDTO(constituency))
                .build();
    }

    @Override
    public Response getAllVoters() {
        List<Voter> voters = voterRepository.findAll();

        List<VoterDTO> voterDTOS = voters.stream().map(entityDTOMapper::mapVoterToVoterDTO).collect(Collectors.toList());

        String message = "Successfully retrieved";
        if (voters == null)  message = "No no voter registered";

        return Response.builder()
                .message(message)
                .voterDTOList(voterDTOS)
                .build();
    }

    @Override
    public Response getVoterById(Long voterNumber) {
       Voter voter  = voterRepository.findByVoterNumber(voterNumber).orElseThrow(() -> new RuntimeException("Voter note found!"));

        return Response.builder()
                .voterDTO(entityDTOMapper.mapVoterToVoterDTO(voter))
                .build();
    }


    @Override
    public Response updateVoter(Long voterNumber, VoterDTO voterDTO) {
        Voter voter  = voterRepository.findByVoterNumber(voterNumber).orElseThrow(() -> new RuntimeException("Voter note found!"));

        if (voterDTO.getVoterNumber() != null ) voter.setVoterNumber(voterDTO.getVoterNumber());
        if (voterDTO.getAge() == voter.getAge()) voter.setAge(voterDTO.getAge());
        if (voterDTO.getGender() != null ) voter.setGender(voterDTO.getGender());
        if (voterDTO.getLastName() != null ) voter.setLastName(voterDTO.getLastName());
        if (voterDTO.getFirstName() != null ) voter.setFirstName(voterDTO.getFirstName());

        voterRepository.save(voter);

        return Response.builder()
                .message("Voter updated successfully")
                .build();
    }

    @Override
    public Response deleteVoter(Long voterNumber) {
        Voter voter  = voterRepository.findByVoterNumber(voterNumber).orElseThrow(() -> new RuntimeException("Voter note found!"));

        voterRepository.delete(voter);

        return Response.builder()
                .message("Voter deleted successfully")
                .build();
    }

    @Override
    public  Response deleteAllVoters() {
        voterRepository.deleteAll();
        return Response.builder().message("All voters deleted successfully").build();
    }
}
