package com.voting.VotingApp.voting_register.repository;

import com.voting.VotingApp.voting_register.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoterRepository extends JpaRepository<Voter, Long> {
    Optional<Voter> findByVoterNumber(Long voterNumber);
    boolean existsByVoterNumber(Long voterNumber);
}
