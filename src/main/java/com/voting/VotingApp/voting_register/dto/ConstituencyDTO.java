package com.voting.VotingApp.voting_register.dto;

import lombok.Data;

@Data
public class ConstituencyDTO {
    private String constituencyName;
    private Long districtId;
    private String constituencyElectoralCode;
    private String constituencyCapital;
    private Long constituencyTotalVotes;
}
