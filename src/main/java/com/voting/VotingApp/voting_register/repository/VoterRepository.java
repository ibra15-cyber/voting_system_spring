package com.voting.VotingApp.voting_register.repository;

import com.voting.VotingApp.voting_register.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Long> {
}
