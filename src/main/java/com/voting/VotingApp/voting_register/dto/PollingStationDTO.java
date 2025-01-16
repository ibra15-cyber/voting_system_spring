package com.voting.VotingApp.voting_register.dto;

import com.voting.VotingApp.voting_register.entity.Constituency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PollingStationDTO {
    private String pollingStationName;
    private String constituencyCode;
    private String pollingStationCode;
    private Long totalVoteCastAtPollingStation;
}
