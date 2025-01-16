package com.voting.VotingApp.voting_register.dto;

import com.voting.VotingApp.voting_register.enums.Gender;
import com.voting.VotingApp.voting_register.enums.PoliticalParty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParliamentaryCandidateDTO {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String constituencyElectoralCode;
    private Integer age;
    private Long voterIdCardNumber;
    private PoliticalParty politicalParty;
    private Long totalVotesAttain;

}
