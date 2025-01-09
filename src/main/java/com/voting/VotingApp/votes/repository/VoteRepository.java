package com.voting.VotingApp.votes.repository;

import com.voting.VotingApp.votes.entity.Vote;
import com.voting.VotingApp.voting_register.dto.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findVotesByConstituencyId(Long constituencyId);
}
