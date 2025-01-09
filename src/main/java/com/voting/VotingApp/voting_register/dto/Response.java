package com.voting.VotingApp.voting_register.dto;

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

}
