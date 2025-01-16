package com.voting.VotingApp.voting_register.seed;

import com.voting.VotingApp.voting_register.dto.VoterDTO;
import com.voting.VotingApp.voting_register.enums.Gender;

import com.voting.VotingApp.voting_register.repository.VoterRepository;
import com.voting.VotingApp.voting_register.service.VoterService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VoterSeed {

    private final VoterService voterService;
    private final VoterRepository voterRepository;

    public VoterSeed(VoterService voterService, VoterRepository voterRepository) {
        this.voterService = voterService;
        this.voterRepository = voterRepository;
    }

    public void seedVoters() {
//             Check if there are existing regions to prevent duplicate seeding
        List<VoterDTO> voterDTOList = List.of(
                //same constituency, diff polling station
                new VoterDTO("Karim", "Toure", Gender.MALE, "A0101", 35, 10384387L, "A010101"),
                new VoterDTO("Karim2", "Toure2", Gender.MALE, "A0101", 43, 897937493L, "A010101"),

                new VoterDTO("Karim3", "Toure3", Gender.MALE, "A0101", 35, 343333L, "A010102"),
                new VoterDTO("Karim4", "Toure4", Gender.MALE, "A0101", 35, 2223434L, "A010102"),

                new VoterDTO("Karim5", "Toure6", Gender.FEMALE, "A0101", 36, 344343L, "A010103"),
                new VoterDTO("Karim7", "Toure7", Gender.MALE, "A0101", 35, 22924343L, "A010103"),
                new VoterDTO("Karim8", "Toure8", Gender.FEMALE, "A0101", 35, 3333343L, "A010103"),
                new VoterDTO("Karim9", "Toure9", Gender.MALE, "A0101", 35, 8974L, "A010103"),
                new VoterDTO("Karim10", "Toure10", Gender.MALE, "A0101", 35, 989080L, "A010103")

                //voters from different constituency: vote for ndc candidate
//                new VoterDTO("constitueny 2 voter 1", "Toure10", Gender.MALE, 2L, 35, "114", 1L),
//                new VoterDTO("constitueny 2 voter 2", "Toure10", Gender.MALE, 2L, 35, "343", 1L),
//                new VoterDTO("constitueny 2 voter 3", "Toure10", Gender.MALE, 2L, 35, "3413", 1L),
//                new VoterDTO("constitueny 2 voter 4", "Toure10", Gender.MALE, 2L, 35, "4343", 1L),
//                new VoterDTO("constitueny 2 voter 5", "Toure10", Gender.MALE, 2L, 35, "2343", 1L)



        );

        voterDTOList.forEach(voterDTO -> {
            if (voterRepository.existsByVoterNumber(voterDTO.getVoterNumber())){
                System.out.println("Voter exists.");
            } else {
                voterService.createVoter(voterDTO);
            }
        });
        System.out.println("Voters seeded successfully.");

    }
}
