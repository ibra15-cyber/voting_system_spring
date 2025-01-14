package com.voting.VotingApp.voting_register.dto;

import com.voting.VotingApp.voting_register.enums.Gender;
import com.voting.VotingApp.voting_register.enums.PoliticalParty;
import lombok.Data;

@Data
public class ParliamentaryCandidateDTO {
    private String firstName;
    private String lastName;
    private Gender gender;
    private Long constituencyId;
    private Integer age;
    private String voterIdCardNumber;
    private PoliticalParty politicalParty;
    private Long totalVotesAttain;
}
