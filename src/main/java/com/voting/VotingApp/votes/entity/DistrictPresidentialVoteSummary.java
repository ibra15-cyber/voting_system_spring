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
public class DistrictPresidentialVoteSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long districtId;

    private Long presidentialCandidateId;

    private Long presidentialCandidateVoteTotal;

}
