package com.voting.VotingApp.voting_register.repository;

import com.voting.VotingApp.voting_register.entity.Constituency;
import com.voting.VotingApp.voting_register.entity.District;
import com.voting.VotingApp.voting_register.entity.ParliamentaryCandidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConstituencyRepository extends JpaRepository<Constituency, Long> {
    List<Constituency> findConstituenciesByDistrict(District district);
}
