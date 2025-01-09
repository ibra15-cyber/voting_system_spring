package com.voting.VotingApp.voting_register.repository;

import com.voting.VotingApp.voting_register.entity.PresidentialCandidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresidentialCandidateRepository extends JpaRepository<PresidentialCandidate, Long> {

}
