package com.voting.VotingApp.voting_register.seed;

import com.voting.VotingApp.votes.dto.VoteDTO;
import com.voting.VotingApp.votes.repository.VoteRepository;
import com.voting.VotingApp.votes.service.VoteService;
import com.voting.VotingApp.voting_register.dto.VoterDTO;
import com.voting.VotingApp.voting_register.entity.Voter;
import com.voting.VotingApp.voting_register.enums.Gender;
import com.voting.VotingApp.voting_register.repository.VoterRepository;
import com.voting.VotingApp.voting_register.service.VoterService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VoteSeed {

    private final VoteService voteService;
    private final VoteRepository voteRepository;
    private final VoterRepository voterRepository;
    public VoteSeed(VoteService voteService, VoteRepository voteRepository, VoterRepository voterRepository, VoterRepository voterRepository1) {
        this.voteService = voteService;
        this.voteRepository = voteRepository;
        this.voterRepository = voterRepository1;
    }


    public void seedVotes() {
//             Check if there are existing regions to prevent duplicate seeding
        List<VoteDTO> voteDTOList = List.of(
                //same constituency, diff polling station
                new VoteDTO(10384387L, 10878348L, 10824334L),
                new VoteDTO(897937493L, 432443L, 10343143L),


                new VoteDTO(11111L, 4324423L, 10824334L),
                new VoteDTO(22222L, 4324423L, 10343143L),
                new VoteDTO(33333L, 4322443L, 10343143L)

//                new VoteDTO("Karim3", "Toure3", Gender.MALE, "A0101", 35, 343333L, "A010102"),
//                new VoteDTO("Karim4", "Toure4", Gender.MALE, "A0101", 35, 2223434L, "A010102"),
//
//                new VoteDTO("Karim5", "Toure6", Gender.FEMALE, "A0101", 36, 344343L, "A010103"),
//                new VoteDTO("Karim7", "Toure7", Gender.MALE, "A0101", 35, 22924343L, "A010103"),
//                new VoteDTO("Karim8", "Toure8", Gender.FEMALE, "A0101", 35, 3333343L, "A010103"),
//                new VoteDTO("Karim9", "Toure9", Gender.MALE, "A0101", 35, 8974L, "A010103"),
//                new VoteDTO("Karim10", "Toure10", Gender.MALE, "A0101", 35, 989080L, "A010103")

                //voters from different constituency: vote for ndc candidate
//                new VoterDTO("constitueny 2 voter 1", "Toure10", Gender.MALE, 2L, 35, "114", 1L),
//                new VoterDTO("constitueny 2 voter 2", "Toure10", Gender.MALE, 2L, 35, "343", 1L),
//                new VoterDTO("constitueny 2 voter 3", "Toure10", Gender.MALE, 2L, 35, "3413", 1L),
//                new VoterDTO("constitueny 2 voter 4", "Toure10", Gender.MALE, 2L, 35, "4343", 1L),
//                new VoterDTO("constitueny 2 voter 5", "Toure10", Gender.MALE, 2L, 35, "2343", 1L)



        );

        voteDTOList.forEach(voteDTO -> {
            Voter voter = voterRepository.findByVoterNumber(voteDTO.getVoterIdNumber()).orElseThrow(()-> new RuntimeException("voter does not exist"));
            if (!voteRepository.existsVoteByVoter(voter)){
                voteService.vote(voteDTO);
            } else {
                System.out.println("Vote exists.");
            }
        });
        System.out.println("Votes seeded successfully.");

    }
}
