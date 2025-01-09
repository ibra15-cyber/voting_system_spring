package com.voting.VotingApp.voting_register.repository;

import com.voting.VotingApp.voting_register.entity.ParliamentaryCandidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParliamentaryCandidateRepository extends JpaRepository<ParliamentaryCandidate, Long> {

}
