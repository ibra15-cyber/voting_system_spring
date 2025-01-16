package com.voting.VotingApp.voting_register.repository;

import com.voting.VotingApp.votes.entity.Vote;
import com.voting.VotingApp.voting_register.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByRegionElectoralCode(String regionElectoralCode);
    boolean existsByRegionElectoralCode(String regionElectoralCode);
}
