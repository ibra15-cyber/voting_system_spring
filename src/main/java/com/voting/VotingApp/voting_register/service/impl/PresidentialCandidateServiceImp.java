package com.voting.VotingApp.voting_register.service.impl;

import com.voting.VotingApp.voting_register.dto.ParliamentaryCandidateDTO;
import com.voting.VotingApp.voting_register.dto.PresidentialCandidateDTO;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.entity.PresidentialCandidate;
import com.voting.VotingApp.voting_register.mapper.EntityDTOMapper;
import com.voting.VotingApp.voting_register.repository.PresidentialCandidateRepository;
import com.voting.VotingApp.voting_register.service.PresidentialCandidateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresidentialCandidateServiceImp implements PresidentialCandidateService {

    private final EntityDTOMapper entityDTOMapper;
    private final PresidentialCandidateRepository presidentialCandidateRepository;

    public PresidentialCandidateServiceImp(
            EntityDTOMapper entityDTOMapper,
            PresidentialCandidateRepository presidentialCandidateRepository) {
        this.entityDTOMapper = entityDTOMapper;
        this.presidentialCandidateRepository = presidentialCandidateRepository;
    }

    @Override
    public Response createPrCandidate(PresidentialCandidateDTO presidentialCandidateDTO) {

        PresidentialCandidate presidentialCandidate = new PresidentialCandidate();

        presidentialCandidate.setFirstName(presidentialCandidateDTO.getFirstName());
        presidentialCandidate.setLastName(presidentialCandidateDTO.getLastName());
        presidentialCandidate.setAge(presidentialCandidateDTO.getAge());
        presidentialCandidate.setGender(presidentialCandidateDTO.getGender().toString());
        presidentialCandidate.setPoliticalParty(presidentialCandidateDTO.getPoliticalParty());

        presidentialCandidateRepository.save(presidentialCandidate);

        return Response.builder()
                .message("Presidential candidate created successfully!")
                .build();
    }

    @Override
    public Response getAllPrCandidates() {
        List<PresidentialCandidate> presidentialCandidates =  presidentialCandidateRepository.findAll();

        List<ParliamentaryCandidateDTO> parliamentaryCandidateDTOList = presidentialCandidates.stream()
                .map(entityDTOMapper::presidentialCandidateToPresidentialCandidateDTO).toList();

        return Response.builder()
                .parliamentaryCandidateDTOList(parliamentaryCandidateDTOList)
                .build();
    }


    @Override
    public Response getPrCandidateById(Long candidateId) {
       PresidentialCandidate presidentialCandidate =  presidentialCandidateRepository.findById(candidateId).orElseThrow(()-> new RuntimeException("Presidential candidate not found!"));

       ParliamentaryCandidateDTO parliamentaryCandidateDTO = entityDTOMapper.presidentialCandidateToPresidentialCandidateDTO(presidentialCandidate);

        return Response.builder()
                .parliamentaryCandidateDTO(parliamentaryCandidateDTO)
                .build();
    }

    @Override
    public Response updatePrCandidate(Long candidateId, PresidentialCandidateDTO presidentialCandidateDTO) {
        PresidentialCandidate presidentialCandidate =  presidentialCandidateRepository.findById(candidateId).orElseThrow(()-> new RuntimeException("Presidential candidate not found!"));

        if (presidentialCandidateDTO.getFirstName() != null) presidentialCandidate.setFirstName(presidentialCandidateDTO.getFirstName());
        if (presidentialCandidateDTO.getLastName() != null) presidentialCandidate.setLastName(presidentialCandidateDTO.getLastName());
        if (presidentialCandidateDTO.getGender() != null) presidentialCandidate.setGender(String.valueOf(presidentialCandidateDTO.getGender()));
        if (presidentialCandidateDTO.getPoliticalParty() != null) presidentialCandidate.setPoliticalParty(presidentialCandidateDTO.getPoliticalParty());
        if (presidentialCandidateDTO.getAge() != null ) presidentialCandidate.setAge(presidentialCandidateDTO.getAge());

        presidentialCandidateRepository.save(presidentialCandidate);

        ParliamentaryCandidateDTO parliamentaryCandidateDTO1 = entityDTOMapper.presidentialCandidateToPresidentialCandidateDTO(presidentialCandidate);

        return Response.builder()
                .parliamentaryCandidateDTO(parliamentaryCandidateDTO1)
                .build();
    }

    @Override
    public Response deletePrCandidate(Long candidateId) {
        PresidentialCandidate presidentialCandidate =  presidentialCandidateRepository.findById(candidateId).orElseThrow(()-> new RuntimeException("Presidential candidate not found!"));

        presidentialCandidateRepository.delete(presidentialCandidate);;

        return Response.builder().message("Candidate deleted successfully!").build();
    }
}
