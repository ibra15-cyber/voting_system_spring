package com.voting.VotingApp.voting.repository;

import com.voting.VotingApp.voting.entity.Constituency;
import com.voting.VotingApp.voting.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
