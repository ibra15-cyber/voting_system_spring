package com.voting.VotingApp.voting_register.repository;

import com.voting.VotingApp.voting_register.entity.Constituency;
import com.voting.VotingApp.voting_register.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConstituencyRepository extends JpaRepository<Constituency, Long> {
    List<Constituency> findConstituenciesByDistrict(District district); //it is not allowing me to use districtId bc they have a direct relationship
    //select *
    //from constituencies
    //where district_id=1;
    Optional<Constituency> findByConstituencyElectoralCode(String electoralCode);
    boolean existsByConstituencyElectoralCode(String electoralCode);
}
