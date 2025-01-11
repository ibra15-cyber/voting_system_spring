package com.voting.VotingApp.votes.entity;

import com.voting.VotingApp.voting_register.enums.PoliticalParty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table()
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegionalPresidentialVoteSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long regionId;

    private Long presidentialCandidateId;

    private PoliticalParty politicalParty;

    private Long presidentialCandidateVoteTotal;

}
