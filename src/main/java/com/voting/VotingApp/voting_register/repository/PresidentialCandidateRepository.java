package com.voting.VotingApp.voting_register.repository;

import com.voting.VotingApp.voting_register.entity.PresidentialCandidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PresidentialCandidateRepository extends JpaRepository<PresidentialCandidate, Long> {
    boolean existsByPresidentialVoterIdNumber(Long presidentialCandidateVoterCardNumber);

    Optional<PresidentialCandidate> findByPresidentialVoterIdNumber(Long voterIdNumber);
}
