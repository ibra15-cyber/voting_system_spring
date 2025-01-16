package com.voting.VotingApp.voting_register.dto;

import com.voting.VotingApp.votes.entity.ConstituencyPresidentialVoteSummary;
import com.voting.VotingApp.votes.entity.DistrictPresidentialVoteSummary;
import com.voting.VotingApp.votes.entity.Vote;
import com.voting.VotingApp.votes.repository.DistrictPresidentialVoteSummaryRepository;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Response {
//    private String vote;
    private String message;
    private RegionDTO regionDTO;
    private List<RegionDTO> regionDTOList;
    private DistrictDTO districtDTO;
    private List<DistrictDTO> districtDTOList;
    private ConstituencyDTO constituencyDTO;
    private List<ConstituencyDTO> constituencyDTOList;
    private VoterDTO voterDTO;
    private List<VoterDTO> voterDTOList;
    private ParliamentaryCandidateDTO parliamentaryCandidateDTO;
    private List<ParliamentaryCandidateDTO> parliamentaryCandidateDTOList;
    private Long parliamentaryTotal;
    private Long totalVoteCastNationwide;
    private  List<ConstituencyPresidentialVoteSummary> presidentialVoteSummaryByConstituencies;
    private List<DistrictPresidentialVoteSummary> presidentialVoteSummaryByDistricts;
    private PollingStationDTO pollingStationDTO;
    private List<PollingStationDTO> pollingStationDTOList;
    private PresidentialCandidateDTO presidentialCandidateDTO;
    private List<PresidentialCandidateDTO> presidentialCandidateDTOList;
    private List<Vote> listAllVotes;

}
