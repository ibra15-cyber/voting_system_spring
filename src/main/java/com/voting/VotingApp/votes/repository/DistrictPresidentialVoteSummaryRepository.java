package com.voting.VotingApp.votes.repository;

import com.voting.VotingApp.votes.entity.ConstituencyPresidentialVoteSummary;
import com.voting.VotingApp.votes.entity.DistrictPresidentialVoteSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DistrictPresidentialVoteSummaryRepository extends JpaRepository<DistrictPresidentialVoteSummary, Long> {
    Optional<DistrictPresidentialVoteSummary> findByDistrictIdAndPresidentialCandidateId(Long districtId, Long presidentialId);

}
