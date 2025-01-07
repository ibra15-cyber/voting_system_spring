package com.voting.VotingApp.voting.dto;

import com.voting.VotingApp.voting.entity.Constituency;
import com.voting.VotingApp.voting.enums.Gender;
import lombok.Data;

@Data
public class VoterDTO {
    private String firstName;
    private String lastName;
    private Gender gender;
    private Long constituencyId;
    private int age;
    private String voterNumber;
}
