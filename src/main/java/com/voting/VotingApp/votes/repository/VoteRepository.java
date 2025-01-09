package com.voting.VotingApp.votes.repository;

import com.voting.VotingApp.votes.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
