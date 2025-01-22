package com.voting.VotingApp.votes.repository;

import com.voting.VotingApp.votes.entity.RegionalPresidentialVoteSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionalPresidentialVoteSummaryRepository extends JpaRepository<RegionalPresidentialVoteSummary, Long> {
//    RegionalPresidentialVoteSummary findByRegionIdAndPresidentialCandidateId(Long regionId, Long presidentialId);
//    findRegionalPresidentialVoteSummariesByRegionIdAndAndPresidentialCandidateId
    boolean existsRegionalPresidentialVoteSummariesByPresidentialCandidateIdAndRegionId(Long presidentialCandidateId, String regionId);
    Optional<RegionalPresidentialVoteSummary> findByRegionId(String regionId);
    Optional<RegionalPresidentialVoteSummary> findByPresidentialCandidateIdAndRegionId(Long presidentialCandidateId, String regionId);
}
