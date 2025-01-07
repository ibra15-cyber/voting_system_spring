package com.voting.VotingApp.voting.repository;

import com.voting.VotingApp.voting.entity.Constituency;
import com.voting.VotingApp.voting.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstituencyRepository extends JpaRepository<Constituency, Long> {
}
