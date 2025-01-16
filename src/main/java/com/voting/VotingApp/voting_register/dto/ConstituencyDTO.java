package com.voting.VotingApp.voting_register.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConstituencyDTO {
    private String constituencyName;
    private String districtElectoralCode;
    private String constituencyElectoralCode;
    private String constituencyCapital;
    private Long constituencyTotalVotes;
}
