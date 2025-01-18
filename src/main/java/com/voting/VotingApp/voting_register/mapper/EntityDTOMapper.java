package com.voting.VotingApp.voting_register.mapper;

import com.voting.VotingApp.voting_register.dto.*;
import com.voting.VotingApp.voting_register.entity.*;
import com.voting.VotingApp.voting_register.enums.Gender;
import com.voting.VotingApp.voting_register.enums.PoliticalParty;
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
//        districtDTO.setRegionId(district.getRegion().getRegionId());
        districtDTO.setDistrictElectoralCode(district.getDistrictElectoralCode());
        districtDTO.setDistrictCapital(district.getDistrictCapital());
        districtDTO.setDistrictElectoralCode(district.getDistrictElectoralCode());
        districtDTO.setRegionElectoralCode(district.getRegion().getRegionElectoralCode());

        return districtDTO;
    }

    public ConstituencyDTO mapConstituencyToConstituencyDTO(Constituency constituency){
        ConstituencyDTO constituencyDTO = new ConstituencyDTO();
        constituencyDTO.setConstituencyName(constituency.getConstituencyName());
        constituencyDTO.setDistrictElectoralCode(constituency.getConstituencyElectoralCode());
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
//        voterDTO.setConstituencyElectoralCode(voter.getConstituencyCode());
        voterDTO.setVoterPollingStationElectoralCode(voter.getPollingStation().getPollingStationCode());

        return voterDTO;
    }

    public ParliamentaryCandidateDTO parliamentaryCandidateToParliamentaryCandidateDTO(ParliamentaryCandidate parliamentaryCandidate) {

        ParliamentaryCandidateDTO parliamentaryCandidateDTO = new ParliamentaryCandidateDTO();

        parliamentaryCandidateDTO.setFirstName(parliamentaryCandidate.getFirstName());
        parliamentaryCandidateDTO.setLastName(parliamentaryCandidate.getLastName());
        parliamentaryCandidateDTO.setGender(Gender.valueOf(parliamentaryCandidate.getGender()));
        parliamentaryCandidateDTO.setAge(parliamentaryCandidate.getAge());
        parliamentaryCandidateDTO.setTotalVotesAttain(parliamentaryCandidate.getTotalVotesAttained());

        parliamentaryCandidateDTO.setConstituencyElectoralCode(parliamentaryCandidate.getConstituency().getConstituencyElectoralCode());
        parliamentaryCandidateDTO.setVoterIdCardNumber(parliamentaryCandidate.getParliamentaryCandidateNumber());
        parliamentaryCandidateDTO.setPoliticalParty(PoliticalParty.valueOf(parliamentaryCandidate.getPoliticalParty()));

        return parliamentaryCandidateDTO;
    }

    public PresidentialCandidateDTO presidentialCandidateToPresidentialCandidateDTO(PresidentialCandidate presidentialCandidate) {

        PresidentialCandidateDTO presidentialCandidateDTO = new PresidentialCandidateDTO();

        presidentialCandidateDTO.setFirstName(presidentialCandidate.getFirstName());
        presidentialCandidateDTO.setLastName(presidentialCandidate.getLastName());
        presidentialCandidateDTO.setGender(Gender.valueOf(presidentialCandidate.getGender()));
        presidentialCandidateDTO.setAge(presidentialCandidate.getAge());
        presidentialCandidateDTO.setPoliticalParty(PoliticalParty.valueOf(presidentialCandidate.getPoliticalParty()));
        presidentialCandidateDTO.setTotalVotesAttain(presidentialCandidate.getTotalVotesAttained());
        presidentialCandidateDTO.setPresidentialIdCardNumber(presidentialCandidate.getPresidentialVoterIdNumber());



        return presidentialCandidateDTO;
    }

    public PollingStationDTO mapPollingStationToPollingStationDTO(PollingStation pollingStation) {
        PollingStationDTO pollingStationDTO = new PollingStationDTO();

        pollingStationDTO.setPollingStationName(pollingStation.getPollingStationName());
        pollingStationDTO.setPollingStationCode(pollingStation.getPollingStationCode());
        pollingStationDTO.setConstituencyCode(pollingStation.getConstituency().getConstituencyElectoralCode());
        pollingStationDTO.setTotalVoteCastAtPollingStation(pollingStation.getTotalVoteCastAtPollingStation());
        return pollingStationDTO;
    }
}
