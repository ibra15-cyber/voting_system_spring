package com.voting.VotingApp.voting_register.repository;

import com.voting.VotingApp.voting_register.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Long> {
}
