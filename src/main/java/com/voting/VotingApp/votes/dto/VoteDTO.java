package com.voting.VotingApp.votes.dto;

import lombok.Data;

@Data
public class VoteDTO {
    private Long voterId;
    private Long constituencyId;
    private Long parliamentaryCandidateId;
    private Long presidentialCandidateId;
    private Long pollingStationId;
}
