package com.voting.VotingApp.votes.repository;

import com.voting.VotingApp.votes.entity.ConstituencyParliamentaryVoteSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstituencyParliamentaryVoteSummaryRepository extends JpaRepository<ConstituencyParliamentaryVoteSummary, Long> {
    boolean existsByParliamentaryCandidateIdAndConstituencyId(Long parliamentaryCandidateId, String constituencyId);
    ConstituencyParliamentaryVoteSummary findByParliamentaryCandidateId(Long parliamentaryCandidateId);

}
