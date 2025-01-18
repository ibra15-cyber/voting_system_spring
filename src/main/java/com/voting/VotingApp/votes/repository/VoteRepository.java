package com.voting.VotingApp.votes.repository;

import com.voting.VotingApp.votes.entity.Vote;
import com.voting.VotingApp.voting_register.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
//    List<Vote> findVotesByConstituencyId(Long constituencyId);


    Vote findVoteByVoter(Voter voter);

    boolean existsVoteByVoter(Voter voter);

//    List<Vote> findVotesByConstituencyElectoralCode(String constituencyElectoralCode);
//    List<Vote> findVoteByPresidentialCandidate(PresidentialCandidate presidentialCandidate);
    List<Vote> findVotesByPollingStation(PollingStation pollingStation);

    List<Vote> findVotesByPresidentialCandidate(PresidentialCandidate presidentialCandidate);

    List<Vote> findVotesByParliamentaryCandidate(ParliamentaryCandidate parliamentaryCandidate);

    List<Vote> findVotesByParliamentaryCandidateAndConstituencyCode(ParliamentaryCandidate parliamentaryCandidate, String constituencyCode);

    List<Vote> findVotesByPresidentialCandidateAndPollingStation(PresidentialCandidate presidentialCandidate, PollingStation pollingStation);

    List<Vote> findVotesByPresidentialCandidateAndConstituencyCode(PresidentialCandidate presidentialCandidate, String constituencyCode);

    List<Vote> findVotesByPresidentialCandidateAndRegionalCode(PresidentialCandidate presidentialCandidate, String regionalCode);

    List<Vote> findVotesByConstituencyCode(String constituencyCode);

}
