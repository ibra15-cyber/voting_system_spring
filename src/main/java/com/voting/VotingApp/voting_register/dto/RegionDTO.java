package com.voting.VotingApp.voting_register.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionDTO {
    private String regionName;
    private String regionElectoralCode;
    private String regionalCapital;
}
