package com.voting.VotingApp.voting_register.seed;

import com.voting.VotingApp.voting_register.dto.PresidentialCandidateDTO;
import com.voting.VotingApp.voting_register.dto.RegionDTO;
import com.voting.VotingApp.voting_register.entity.PresidentialCandidate;
import com.voting.VotingApp.voting_register.enums.Gender;
import com.voting.VotingApp.voting_register.enums.PoliticalParty;
import com.voting.VotingApp.voting_register.repository.PresidentialCandidateRepository;
import com.voting.VotingApp.voting_register.service.PresidentialCandidateService;
import com.voting.VotingApp.voting_register.service.RegionService;
import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForReadableInstant;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PresidentialCandidateSeed {

    private final PresidentialCandidateService presidentialCandidateService;
    private final PresidentialCandidateRepository presidentialCandidateRepository;

    public PresidentialCandidateSeed(PresidentialCandidateService presidentialCandidateService,  PresidentialCandidateRepository presidentialCandidateRepository) {
        this.presidentialCandidateService = presidentialCandidateService;
        this.presidentialCandidateRepository = presidentialCandidateRepository;
    }

    public void seedPresidentialCandidates() {
//             Check if there are existing regions to prevent duplicate seeding
        List<PresidentialCandidateDTO> presidentialCandidateDTOList = List.of(
                new PresidentialCandidateDTO("Mahamudu", "Bawumia", Gender.MALE, 49,  10824334L, PoliticalParty.NPP, null),
                new PresidentialCandidateDTO("John", "Mahama", Gender.MALE, 54,  10343143L, PoliticalParty.NDC, null)
        );

        presidentialCandidateDTOList.forEach(presidentialCandidateDTO -> {
            if (presidentialCandidateRepository.existsByPresidentialVoterIdNumber(presidentialCandidateDTO.getPresidentialIdCardNumber())){
                System.out.println("presidential candidate exists");
            } else {
                presidentialCandidateService.createPrCandidate(presidentialCandidateDTO);
            }
        });
        System.out.println("Presidential Candidate seeded successfully.");

    }
}
