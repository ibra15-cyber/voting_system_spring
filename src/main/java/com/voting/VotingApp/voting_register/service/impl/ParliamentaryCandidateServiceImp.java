package com.voting.VotingApp.voting_register.service.impl;

import com.voting.VotingApp.voting_register.dto.ParliamentaryCandidateDTO;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.entity.*;
import com.voting.VotingApp.voting_register.mapper.EntityDTOMapper;
import com.voting.VotingApp.voting_register.repository.ParliamentaryCandidateRepository;
import com.voting.VotingApp.voting_register.repository.ConstituencyRepository;
import com.voting.VotingApp.voting_register.repository.VoterRepository;
import com.voting.VotingApp.voting_register.service.ParliamentaryCandidateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParliamentaryCandidateServiceImp implements ParliamentaryCandidateService {

    private final ConstituencyRepository constituencyRepository;
    private final EntityDTOMapper entityDTOMapper;
    private final ParliamentaryCandidateRepository parliamentaryCandidateRepository;

    public ParliamentaryCandidateServiceImp(ConstituencyRepository constituencyRepository,
                                            EntityDTOMapper entityDTOMapper,
                                            ParliamentaryCandidateRepository parliamentaryCandidateRepository) {
        this.constituencyRepository = constituencyRepository;
        this.entityDTOMapper = entityDTOMapper;
        this.parliamentaryCandidateRepository = parliamentaryCandidateRepository;
    }

//    @Override
//    public Response createVoter(VoterDTO voterDTO) {
//
//        System.out.println(voterDTO);
//
//        Voter newVoter = new Voter();
//        System.out.println(voterDTO);
//
//        newVoter.setAge(voterDTO.getAge());
//        newVoter.setGender((voterDTO.getGender()));
//        newVoter.setFirstName(voterDTO.getFirstName());
//        newVoter.setLastName(voterDTO.getLastName());
//        newVoter.setVoterNumber(voterDTO.getVoterNumber());
//
//        Constituency constituency = constituencyRepository.findById(voterDTO.getConstituencyId()).orElseThrow( () -> new RuntimeException("Constituency does not exist"));
//        newVoter.setConstituency(constituency);
//
//        District district = constituency.getDistrict();
//        Region region = district.getRegion();
//
//
//
//        voterRepository.save(newVoter);
//
//        return Response.builder()
//                .message("Voter created successfully!")
//                .regionDTO( entityDTOMapper.mapRegionToRegionDTO(region))
//                .districtDTO(entityDTOMapper.mapDistrictToDistrictDTO(district))
//                .constituencyDTO(entityDTOMapper.mapConstituencyToContituencyDTO(constituency))
//                .build();
//    }
//
//    @Override
//    public Response getAllVoters() {
//        List<Voter> voters = voterRepository.findAll();
//
//        List<VoterDTO> voterDTOS = voters.stream().map(entityDTOMapper::mapVoterToVoterDTO).collect(Collectors.toList());
//        return Response.builder()
//                .voterDTOList(voterDTOS)
//                .build();
//    }
//
//    @Override
//    public Response getVoterById(Long voterId) {
//       Voter voter  = voterRepository.findById(voterId).orElseThrow(() -> new RuntimeException("Voter note found!"));
//
//        return Response.builder()
//                .voterDTO(entityDTOMapper.mapVoterToVoterDTO(voter))
//                .build();
//    }
//
//
//    @Override
//    public Response updateVoter(Long voterId, VoterDTO voterDTO) {
//        Voter voter  = voterRepository.findById(voterId).orElseThrow(() -> new RuntimeException("Voter note found!"));
//
//        if (voterDTO.getVoterNumber() != null ) voter.setVoterNumber(voterDTO.getVoterNumber());
//        if (voterDTO.getAge() == voter.getAge()) voter.setAge(voterDTO.getAge());
//        if (voterDTO.getGender() != null ) voter.setGender(voterDTO.getGender());
//        if (voterDTO.getLastName() != null ) voter.setLastName(voterDTO.getLastName());
//        if (voterDTO.getFirstName() != null ) voter.setFirstName(voterDTO.getFirstName());
//
//        voterRepository.save(voter);
//
//        return Response.builder()
//                .build();
//    }
//
//    @Override
//    public Response deleteVoter(Long voterId) {
//        Voter voter  = voterRepository.findById(voterId).orElseThrow(() -> new RuntimeException("Voter note found!"));
//
//        voterRepository.delete(voter);
//
//        return Response.builder()
//                .message("Voter deleted successfully")
//                .build();
//    }


    @Override
    public Response createCandidate(ParliamentaryCandidateDTO parliamentaryCandidateDTO) {

        System.out.println(parliamentaryCandidateDTO);
        ParliamentaryCandidate parliamentaryCandidate = new ParliamentaryCandidate();

        parliamentaryCandidate.setFirstName(parliamentaryCandidateDTO.getFirstName());
        parliamentaryCandidate.setLastName(parliamentaryCandidateDTO.getLastName());
        parliamentaryCandidate.setAge(parliamentaryCandidateDTO.getAge());
        parliamentaryCandidate.setGender(parliamentaryCandidateDTO.getGender().toString());
        parliamentaryCandidate.setPoliticalParty(parliamentaryCandidateDTO.getPoliticalParty());

        //don't do this is mapper. this means we can use id from dto to get us an object in service to map with real dto data
        Constituency constituency= constituencyRepository.findById(parliamentaryCandidateDTO.getConstituencyId()).orElseThrow(()-> new RuntimeException("Constituency not found"));
        parliamentaryCandidate.setConstituency(constituency);


        parliamentaryCandidateRepository.save(parliamentaryCandidate);

        return Response.builder()
                .message("Parliamentary candidate created successfully!")
                .build();
    }

    @Override
    public Response getAllCandidates() {
        List<ParliamentaryCandidate> parliamentaryCandidates =  parliamentaryCandidateRepository.findAll();

        List<ParliamentaryCandidateDTO> parliamentaryCandidateDTOList = parliamentaryCandidates.stream()
                .map(entityDTOMapper::parliamentaryCandidateToParliamentaryCandidateDTO).toList();

        //check it later
//        String message = "Successfully retrieved";
//        if (parliamentaryCandidates == null)  message = "No parliamentary candidate registered";

        return Response.builder()
                .parliamentaryCandidateDTOList(parliamentaryCandidateDTOList)
                .build();
    }


    @Override
    public Response getCandidateById(Long candidateId) {
        return null;
    }

    @Override
    public Response updateCandidate(Long candidateId, ParliamentaryCandidateDTO parliamentaryCandidateDTO) {
        return null;
    }

    @Override
    public Response deleteCandidate(Long candidateId) {
        return null;
    }
}
