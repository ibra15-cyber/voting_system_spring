package com.voting.VotingApp.voting_register.repository;

import com.voting.VotingApp.voting_register.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
