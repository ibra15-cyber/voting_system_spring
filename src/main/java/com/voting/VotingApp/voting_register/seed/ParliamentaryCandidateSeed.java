package com.voting.VotingApp.voting_register.seed;

import com.voting.VotingApp.voting_register.dto.ParliamentaryCandidateDTO;
import com.voting.VotingApp.voting_register.dto.PresidentialCandidateDTO;
import com.voting.VotingApp.voting_register.enums.Gender;
import com.voting.VotingApp.voting_register.enums.PoliticalParty;
import com.voting.VotingApp.voting_register.repository.ParliamentaryCandidateRepository;
import com.voting.VotingApp.voting_register.service.ParliamentaryCandidateService;
import com.voting.VotingApp.voting_register.service.PresidentialCandidateService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParliamentaryCandidateSeed {

    private final ParliamentaryCandidateService parliamentaryCandidateService;
    private final ParliamentaryCandidateRepository parliamentaryCandidateRepository;

    public ParliamentaryCandidateSeed(ParliamentaryCandidateService parliamentaryCandidateService, ParliamentaryCandidateRepository parliamentaryCandidateRepository) {
        this.parliamentaryCandidateService = parliamentaryCandidateService;
        this.parliamentaryCandidateRepository = parliamentaryCandidateRepository;
    }

    public void seedPresidentialCandidates() {
//             Check if there are existing regions to prevent duplicate seeding
        List<ParliamentaryCandidateDTO> parliamentaryCandidateDTOList = List.of(
                new ParliamentaryCandidateDTO("NPP parliamentary CONST 1", "Npp", Gender.MALE, "A0101", 34,  10878348L, PoliticalParty.NPP, null),
                new ParliamentaryCandidateDTO("NDC parliamentary CONST 1", "ndc", Gender.MALE, "A0101", 83,  432443L, PoliticalParty.NDC, null),

                //same district different constituencies
                //what shows they are from the diff constituency? constituencyId
                new ParliamentaryCandidateDTO("NPP parliamentary CONST 2", "npp2", Gender.MALE, "A0201", 43,  4324423L, PoliticalParty.NPP, null),
                new ParliamentaryCandidateDTO("NDC parliamentary CONS 2", "ndc2", Gender.MALE, "A0201", 23,  4322443L, PoliticalParty.NDC, null)

                //same region different constituencies
                //what shows they are in the same region ? nothing, constituency ids are always unique


                //different constituencies
                //what shows they are in different regions? again nothing bc, the descriptor is constituencyId
        );


        parliamentaryCandidateDTOList.forEach(parliamentaryCandidateDTO -> {
            if (parliamentaryCandidateRepository.existsByParliamentaryCandidateNumber(parliamentaryCandidateDTO.getVoterIdCardNumber())){
                System.out.println("Parliamentary candidate exists");
            } else  {
                parliamentaryCandidateService.createCandidate(parliamentaryCandidateDTO);
            }
        });
        System.out.println("Presidential Candidate seeded successfully.");

    }
}
