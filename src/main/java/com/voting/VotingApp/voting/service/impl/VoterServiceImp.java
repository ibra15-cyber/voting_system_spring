package com.voting.VotingApp.voting.service.impl;

import com.voting.VotingApp.voting.dto.VoterDTO;
import com.voting.VotingApp.voting.entity.Constituency;
import com.voting.VotingApp.voting.entity.Voter;
import com.voting.VotingApp.voting.dto.Response;
import com.voting.VotingApp.voting.repository.ConstituencyRepository;
import com.voting.VotingApp.voting.repository.VoterRepository;
import com.voting.VotingApp.voting.service.VoterService;
import org.springframework.stereotype.Service;

@Service
public class VoterServiceImp implements VoterService {

    private final VoterRepository voterRepository;
    private final ConstituencyRepository constituencyRepository;

    public VoterServiceImp(VoterRepository voterRepository, ConstituencyRepository constituencyRepository) {
        this.voterRepository = voterRepository;
        this.constituencyRepository = constituencyRepository;
    }

    @Override
    public Response getAllVoters() {
        return null;
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
        
        Constituency constituency = constituencyRepository.findById(voterDTO.getConstituencyId()).orElseThrow( () -> new RuntimeException("Constituency does not exist"));
        newVoter.setConstituency(constituency);

        voterRepository.save(newVoter);
        return Response.builder()
                .message("Voter created successfully!")
                .build();
    }
}
