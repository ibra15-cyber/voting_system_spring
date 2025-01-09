package com.voting.VotingApp.voting_register.dto;

import lombok.Data;

@Data
public class DistrictDTO {
    private String districtName;
    private Long regionId;
    private String districtElectoralCode;
    private String districtCapital;

}
