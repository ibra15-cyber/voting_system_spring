package com.voting.VotingApp.voting.repository;

import com.voting.VotingApp.voting.entity.District;
import com.voting.VotingApp.voting.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Long> {
}
