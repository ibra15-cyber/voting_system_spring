package com.voting.VotingApp.voting_register.repository;

import com.voting.VotingApp.voting_register.entity.District;
import com.voting.VotingApp.voting_register.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District> findDistrictsByRegion(Region region);
    List<District> findByDistrictIdAndRegion(Long districtId, Region region);

    Optional<District> findByDistrictElectoralCode(String electoralCode);
    boolean existsByDistrictElectoralCode(String electoralCode);
}
