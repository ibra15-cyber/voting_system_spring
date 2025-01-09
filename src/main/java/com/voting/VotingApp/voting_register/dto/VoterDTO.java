package com.voting.VotingApp.voting_register.dto;

import com.voting.VotingApp.voting_register.enums.Gender;
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
