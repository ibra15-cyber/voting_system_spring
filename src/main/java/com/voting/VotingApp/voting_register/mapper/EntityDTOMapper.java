package com.voting.VotingApp.voting_register.mapper;

import com.voting.VotingApp.voting_register.dto.*;
import com.voting.VotingApp.voting_register.entity.*;
import com.voting.VotingApp.voting_register.enums.Gender;
import com.voting.VotingApp.voting_register.repository.ConstituencyRepository;
import com.voting.VotingApp.voting_register.repository.VoterRepository;
import org.springframework.stereotype.Component;

@Component
public class EntityDTOMapper {

    private final VoterRepository voterRepository;
    private final ConstituencyRepository constituencyRepository;

    public EntityDTOMapper(VoterRepository voterRepository, ConstituencyRepository constituencyRepository) {
        this.voterRepository = voterRepository;
        this.constituencyRepository = constituencyRepository;
    }

    public RegionDTO mapRegionToRegionDTO(Region region) {
        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setRegionName(region.getRegionName());
        regionDTO.setRegionalCapital(region.getRegionalCapital());
        regionDTO.setRegionElectoralCode(region.getRegionElectoralCode());

        return regionDTO;
    }

    public DistrictDTO mapDistrictToDistrictDTO(District district){

        DistrictDTO districtDTO = new DistrictDTO();

        districtDTO.setDistrictName(district.getDistrictName());
        districtDTO.setRegionId(district.getRegion().getRegionId());
        districtDTO.setDistrictElectoralCode(district.getDistrictElectoralCode());
        districtDTO.setDistrictCapital(district.getDistrictCapital());

        return districtDTO;
    }

    public ConstituencyDTO mapConstituencyToContituencyDTO(Constituency constituency){
        ConstituencyDTO constituencyDTO = new ConstituencyDTO();
        constituencyDTO.setConstituencyName(constituency.getConstituencyName());
        constituencyDTO.setDistrictId(constituency.getConstituencyId());
        constituencyDTO.setConstituencyCapital(constituency.getConstituencyCapital());
        constituencyDTO.setConstituencyElectoralCode(constituency.getConstituencyElectoralCode());
        constituencyDTO.setConstituencyTotalVotes(constituency.getConstituencyTotalVotesCast());

        return constituencyDTO;
    }

    public VoterDTO mapVoterToVoterDTO(Voter voter) {
        VoterDTO voterDTO = new VoterDTO();

        voterDTO.setVoterNumber(voter.getVoterNumber());
        voterDTO.setAge(voter.getAge());
        voterDTO.setGender(voter.getGender());
        voterDTO.setFirstName(voter.getFirstName());
        voterDTO.setLastName(voter.getLastName());
        voterDTO.setConstituencyId(voter.getConstituency().getConstituencyId());

        return voterDTO;
    }

    public ParliamentaryCandidateDTO parliamentaryCandidateToParliamentaryCandidateDTO(ParliamentaryCandidate parliamentaryCandidate) {

        ParliamentaryCandidateDTO parliamentaryCandidateDTO = new ParliamentaryCandidateDTO();

        parliamentaryCandidateDTO.setFirstName(parliamentaryCandidate.getFirstName());
        parliamentaryCandidateDTO.setLastName(parliamentaryCandidate.getLastName());
        parliamentaryCandidateDTO.setGender(Gender.valueOf(parliamentaryCandidate.getGender()));
        parliamentaryCandidateDTO.setAge(parliamentaryCandidate.getAge());
        parliamentaryCandidateDTO.setTotalVotesAttain(parliamentaryCandidate.getTotalVotesAttained());

        parliamentaryCandidateDTO.setConstituencyId(parliamentaryCandidate.getConstituency().getConstituencyId());

        return parliamentaryCandidateDTO;
    }

    public ParliamentaryCandidateDTO presidentialCandidateToPresidentialCandidateDTO(PresidentialCandidate presidentialCandidate) {

        ParliamentaryCandidateDTO parliamentaryCandidateDTO = new ParliamentaryCandidateDTO();

        parliamentaryCandidateDTO.setFirstName(presidentialCandidate.getFirstName());
        parliamentaryCandidateDTO.setLastName(presidentialCandidate.getLastName());
        parliamentaryCandidateDTO.setGender(Gender.valueOf(presidentialCandidate.getGender()));
        parliamentaryCandidateDTO.setAge(presidentialCandidate.getAge());
        parliamentaryCandidateDTO.setTotalVotesAttain(presidentialCandidate.getTotalVotesAttained());


        return parliamentaryCandidateDTO;
    }
}
