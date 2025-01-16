package com.voting.VotingApp.votes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class VoteDTO {
    private Long voterIdNumber;
//    private String constituencyCode;
    private Long parliamentaryCandidateId;
    private Long presidentialCandidateId;
//    private String pollingStationCode;
}
