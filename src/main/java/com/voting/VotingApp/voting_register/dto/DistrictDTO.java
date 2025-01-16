package com.voting.VotingApp.voting_register.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDTO {
    private String districtName;
    private String regionElectoralCode;
    private String districtElectoralCode;
    private String districtCapital;

}
