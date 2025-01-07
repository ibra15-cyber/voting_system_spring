package com.voting.VotingApp.voting.dto;

import com.voting.VotingApp.voting.entity.Region;
import lombok.Data;

@Data
public class DistrictDTO {
    private String districtName;
    private Long regionId;
}
