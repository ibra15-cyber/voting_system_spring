package com.voting.VotingApp.voting_register.repository;

import com.voting.VotingApp.voting_register.entity.PollingStation;
import com.voting.VotingApp.voting_register.entity.District;
import com.voting.VotingApp.voting_register.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PollingStationRepository extends JpaRepository<PollingStation, Long> {
//    List<PollingStation> findConstituenciesByDistrict(District district); //it is not allowing me to use districtId bc they have a direct relationship
    //select *
    //from constituencies
    //where district_id=1;

    Optional<PollingStation>  findByPollingStationCode(String pollingElectoralCode);
    boolean existsByPollingStationCode(String pollingElectoralCode);
    Optional<PollingStation> findPollingStationByVoters(Voter voter);

}
