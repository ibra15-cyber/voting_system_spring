package com.voting.VotingApp.votes.repository;

import com.voting.VotingApp.votes.entity.DistrictPresidentialVoteSummary;
import com.voting.VotingApp.votes.entity.RegionalPresidentialVoteSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionalPresidentialVoteSummaryRepository extends JpaRepository<RegionalPresidentialVoteSummary, Long> {
    RegionalPresidentialVoteSummary findRegionalPresidentialVoteSummariesByRegionIdAndAndPresidentialCandidateId(Long regionId, Long presidentialId);
}
