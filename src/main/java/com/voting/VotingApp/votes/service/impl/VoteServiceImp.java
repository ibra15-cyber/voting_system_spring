package com.voting.VotingApp.votes.service.impl;

import com.voting.VotingApp.votes.dto.VoteDTO;
import com.voting.VotingApp.votes.entity.Vote;
import com.voting.VotingApp.votes.repository.VoteRepository;
import com.voting.VotingApp.votes.service.VoteService;
import com.voting.VotingApp.voting_register.dto.Response;
import com.voting.VotingApp.voting_register.entity.Constituency;
import com.voting.VotingApp.voting_register.entity.ParliamentaryCandidate;
import com.voting.VotingApp.voting_register.entity.Voter;
import com.voting.VotingApp.voting_register.repository.ConstituencyRepository;
import com.voting.VotingApp.voting_register.repository.ParliamentaryCandidateRepository;
import com.voting.VotingApp.voting_register.repository.VoterRepository;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImp implements VoteService {
    private final VoteRepository voteRepository;
    private final VoterRepository voterRepository;
    private final ConstituencyRepository constituencyRepository;
    private final ParliamentaryCandidateRepository parliamentaryCandidateRepository;

    public VoteServiceImp(VoteRepository voteRepository, VoterRepository voterRepository, ConstituencyRepository constituencyRepository, ParliamentaryCandidateRepository parliamentaryCandidateRepository) {
        this.voteRepository = voteRepository;
        this.voterRepository = voterRepository;
        this.constituencyRepository = constituencyRepository;
        this.parliamentaryCandidateRepository = parliamentaryCandidateRepository;
    }

    @Override
    public Response vote(VoteDTO voteDTO) {
        System.out.println(voteDTO);

        Vote vote = new Vote();

        Voter voter = voterRepository.findById(voteDTO.getVoterId()).orElseThrow(()-> new RuntimeException("Voter not found"));
        vote.setVoter(voter);

//        Constituency constituency = constituencyRepository.findById(voteDTO.getConstituencyId()).orElseThrow(()-> new RuntimeException("Constituency not found"));
//
//        vote.setConstituencyId(constituency);
        ParliamentaryCandidate parliamentaryCandidate = parliamentaryCandidateRepository.findById(voteDTO.getParliamentaryCandidateId()).orElseThrow(()-> new RuntimeException("Parlimentary candidate does not found"));
        vote.setParliamentaryCandidate(parliamentaryCandidate);

        //using the candidate to pull his constituency; we could use the voter
        vote.setConstituencyId(parliamentaryCandidate.getConstituency().getConstituencyId());

        voteRepository.save(vote);

        return Response.builder().
                message("Vote successfully cast")
                .build();
    }
}
