package com.voting.VotingApp.votes.repository;

import com.voting.VotingApp.votes.entity.Vote;
import com.voting.VotingApp.voting_register.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface VoteRepository extends JpaRepository<Vote, Long> {
//    List<Vote> findVotesByConstituencyId(Long constituencyId);

    Vote findVoteByVoter(Voter voter);

    boolean existsVoteByVoter(Voter voter);

//    List<Vote> findVotesByConstituencyElectoralCode(String constituencyElectoralCode);
//    List<Vote> findVoteByPresidentialCandidate(PresidentialCandidate presidentialCandidate);
//List<Vote> findVotesByPollingStation(PollingStation pollingStation);
    @Query(value =
            "SELECT count(*) AS voteTotal " +
                    "FROM votes " +
                    "WHERE polling_electoral_code = :pollingElectoralCode "
//                    "GROUP BY polling_electoral_code"
            ,
            nativeQuery = true)
    Long findTotalVotesByPollingStation(@Param("pollingElectoralCode") String pollingElectoralCode);


//    List<Vote> findVotesByPresidentialCandidate(PresidentialCandidate presidentialCandidate);
    @Query(value =
            "SELECT count(*) AS total " +
                "FROM votes " +
                "WHERE votes.presidential_candidate_id  = :presidentialCandidateId",
            nativeQuery = true)
    Long findTotalVotesForPresidentialCandidate(@Param("presidentialCandidateId") Long presidentialCandidateId);

//    List<Vote> findVotesByParliamentaryCandidate(ParliamentaryCandidate parliamentaryCandidate);
    @Query(value =
        "SELECT count(*) " +
                "FROM votes " +
                "WHERE votes.polling_electoral_code = :pollingStationCode AND votes.parlimentary_candidate_id = :parliamentaryCandidateId ",
    nativeQuery = true)
    Long findTotalVotesForParliamentaryCandidateAtAPollingStation(@Param("pollingStationCode") String pollingStationCode, @Param("parliamentaryCandidateId") Long parliamentaryCandidateNumber);

//    List<Vote> findVotesByParliamentaryCandidateAndConstituencyCode(ParliamentaryCandidate parliamentaryCandidate, String constituencyCode);
    @Query(value =
        "SELECT count(*) " +
                "FROM votes " +
                "WHERE votes.parlimentary_candidate_id = :parliamentaryCandidateId AND votes.constituency_code = :constituencyCode",
    nativeQuery = true)
    Long findTotalVotesForParliamentaryCandidateAtAConstituency(@Param("parliamentaryCandidateId") Long parliamentaryCandidateId, @Param("constituencyCode") String constituencyCode);

//    List<Vote> findVotesByPresidentialCandidateAndPollingStation(PresidentialCandidate presidentialCandidate, PollingStation pollingStation);
    @Query(value =
        "SELECT count(*)" +
                "FROM votes " +
                "WHERE votes.presidential_candidate_id = :presidentialCandidateId AND votes.polling_electoral_code = :pollingStationElectoralCode",
    nativeQuery = true)
    Long totalVotesByPresidentialCandidateAtAPollingStation(@Param("presidentialCandidateId") Long presidentialCandidateId, @Param("pollingStationElectoralCode") String pollingStationElectoralCode);
//    List<Vote> findVotesByPresidentialCandidateAndConstituencyCode(PresidentialCandidate presidentialCandidate, String constituencyCode);

//    List<Vote> findVotesByPresidentialCandidateAndRegionalCode(PresidentialCandidate presidentialCandidate, String regionalCode);
    @Query(value =
        "SELECT count(*) as total " +
                "FROM votes " +
                "WHERE votes.presidential_candidate_Id = :presidentialCandidateId AND votes.regional_code = :regionElectoralCode"
            , nativeQuery = true)
    Long totalVotesForPresidentialCandidateForARegion(@Param("presidentialCandidateId") Long presidentialCandidateId, @Param("regionElectoralCode") String regionElectoralCode);

//    List<Vote> findVotesByConstituencyCode(String constituencyCode);
    @Query(value =
        "SELECT count(*) " +
                "FROM votes " +
                "WHERE votes.constituency_code = :constituencyCode",
    nativeQuery = true)
    Long totalVotesByConstituency(@Param("constituencyCode") String constituencyCode);

//    boolean existsByParliamentaryCandidateAndAndConstituencyCode(ParliamentaryCandidate parliamentaryCandidate, String constituencyCode);

    @Query(value =
        "SELECT count(*)" +
                "FROM votes ", nativeQuery = true)
    Long totalVotesCast();


}
