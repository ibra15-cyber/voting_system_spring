package com.voting.VotingApp.votes.repository;

import com.voting.VotingApp.votes.entity.ConstituencyPresidentialVoteSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConstituencyPresidentialVoteSummaryRepository extends JpaRepository<ConstituencyPresidentialVoteSummary, Long> {

    boolean existsConstituencyPresidentialVoteSummaryByPresidentialCandidateIdAndConstituencyId(Long presidentialCandidateId, String constituencyCode);

    Optional<ConstituencyPresidentialVoteSummary> findConstituencyPresidentialVoteSummaryByPresidentialCandidateIdAndConstituencyId(Long presidentialCandidateId, String constituencyCode);
}

//searching constituency alone will not separate btw different candidates in the constituency
//optional bc we want to tap on the else part to create an object of it doen'st exist yet; else we update
//        select *
//        from constituency_presidential_vote_summary
//        where constituency_id = 1 And presidential_candidate_id=1;
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    ConstituencyPresidentialVoteSummary findByConstituencyIdAndPresidentialCandidateId(Long constituencyId, Long presidentialId);
//    List<ConstituencyPresidentialVoteSummary> findByConstituencyIdAndDistrictId(Long constituencyId, Long districtId); //possible using ids because in this case we are using placeholders
//    List<ConstituencyPresidentialVoteSummary> findAllByConstituencyId(List<Long> constituencyId);
