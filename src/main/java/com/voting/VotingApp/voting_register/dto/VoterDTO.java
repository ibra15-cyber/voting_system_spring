package com.voting.VotingApp.voting_register.dto;

import com.voting.VotingApp.voting_register.entity.PollingStation;
import com.voting.VotingApp.voting_register.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoterDTO {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String constituencyElectoralCode; //changing or removing this means the all computations change, because aggregations depends on votes in  constituency
    private Integer age;
    private Long voterNumber;
    private String voterPollingStationElectoralCode; //keep the constituencyElectoralCode, and then use voterPollingStation to ensure a voter belongs to a polling station
}
