package com.voting.VotingApp.voting.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Response {
//    private String vote;
    private String message;
    private RegionDTO regionDTO;
    private DistrictDTO districtDTO;
}
