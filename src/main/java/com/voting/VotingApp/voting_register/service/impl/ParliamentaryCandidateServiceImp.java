package com.voting.VotingApp.voting_register.service.impl;

import com.voting.VotingApp.voting_register.dto.ParliamentaryCandidateDTO;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.entity.*;
import com.voting.VotingApp.voting_register.mapper.EntityDTOMapper;
import com.voting.VotingApp.voting_register.repository.ParliamentaryCandidateRepository;
import com.voting.VotingApp.voting_register.repository.ConstituencyRepository;
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

    @Override
    public Response createCandidate(ParliamentaryCandidateDTO parliamentaryCandidateDTO) {

        ParliamentaryCandidate parliamentaryCandidate = new ParliamentaryCandidate();

        parliamentaryCandidate.setFirstName(parliamentaryCandidateDTO.getFirstName());
        parliamentaryCandidate.setLastName(parliamentaryCandidateDTO.getLastName());
        parliamentaryCandidate.setAge(parliamentaryCandidateDTO.getAge());
        parliamentaryCandidate.setGender(parliamentaryCandidateDTO.getGender().toString());
        parliamentaryCandidate.setPoliticalParty(parliamentaryCandidateDTO.getPoliticalParty().toString());
        parliamentaryCandidate.setParliamentaryCandidateNumber(parliamentaryCandidateDTO.getVoterIdCardNumber());

        //don't do this is mapper. this means we can use id from dto to get us an object in service to map with real dto data
        Constituency constituency= constituencyRepository.findConstituencyByConstituencyElectoralCode(parliamentaryCandidateDTO.getConstituencyElectoralCode()).orElseThrow(()-> new RuntimeException("Constituency not found"));
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
       ParliamentaryCandidate parliamentaryCandidate =  parliamentaryCandidateRepository.findByParliamentaryCandidateNumber(candidateId).orElseThrow(()-> new RuntimeException("Parliamentary candidate not found!"));

       ParliamentaryCandidateDTO parliamentaryCandidateDTO = entityDTOMapper.parliamentaryCandidateToParliamentaryCandidateDTO(parliamentaryCandidate);

        return Response.builder()
                .parliamentaryCandidateDTO(parliamentaryCandidateDTO)
                .build();
    }

    @Override
    public Response updateCandidate(Long candidateId, ParliamentaryCandidateDTO parliamentaryCandidateDTO) {
        ParliamentaryCandidate parliamentaryCandidate =  parliamentaryCandidateRepository.findByParliamentaryCandidateNumber(candidateId).orElseThrow(()-> new RuntimeException("Parliamentary candidate not found!"));

        if (parliamentaryCandidateDTO.getFirstName() != null) parliamentaryCandidate.setFirstName(parliamentaryCandidateDTO.getFirstName());
        if (parliamentaryCandidateDTO.getLastName() != null) parliamentaryCandidate.setLastName(parliamentaryCandidateDTO.getLastName());
        if (parliamentaryCandidateDTO.getGender() != null) parliamentaryCandidate.setGender(parliamentaryCandidateDTO.getGender().toString());
        if (parliamentaryCandidateDTO.getPoliticalParty() != null) parliamentaryCandidate.setPoliticalParty(parliamentaryCandidateDTO.getPoliticalParty().toString());
        if (parliamentaryCandidateDTO.getAge() != null ) parliamentaryCandidate.setAge(parliamentaryCandidateDTO.getAge());

        if (parliamentaryCandidateDTO.getConstituencyElectoralCode() != null) {
            Constituency constituency = constituencyRepository.findConstituencyByConstituencyElectoralCode(parliamentaryCandidateDTO.getConstituencyElectoralCode()).orElseThrow(() -> new RuntimeException("Constituency not found"));
            parliamentaryCandidate.setConstituency(constituency);
        }

        parliamentaryCandidateRepository.save(parliamentaryCandidate);

        ParliamentaryCandidateDTO parliamentaryCandidateDTO1 = entityDTOMapper.parliamentaryCandidateToParliamentaryCandidateDTO(parliamentaryCandidate);


        return Response.builder()
                .parliamentaryCandidateDTO(parliamentaryCandidateDTO1)
                .build();
    }

    @Override
    public Response deleteCandidate(Long candidateId) {
        ParliamentaryCandidate parliamentaryCandidate =  parliamentaryCandidateRepository.findByParliamentaryCandidateNumber(candidateId).orElseThrow(()-> new RuntimeException("Parliamentary candidate not found!"));

        parliamentaryCandidateRepository.delete(parliamentaryCandidate);;

        return Response.builder().message("Candidate deleted successfully!").build();
    }

    @Override
    public Response deleteAllPresidentialCandidates() {
        parliamentaryCandidateRepository.deleteAll();
        return Response.builder().message("All parliamentary canddiates deleted").build();
    }
}
