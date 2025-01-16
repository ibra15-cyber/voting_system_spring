package com.voting.VotingApp.voting_register.entity;

import com.voting.VotingApp.votes.entity.Vote;
import com.voting.VotingApp.voting_register.enums.PoliticalParty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "presidential_candidates")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PresidentialCandidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long presidentialCandidateId;
    private String firstName;
    private String lastName;

    private String gender;
    private int age;

    @Column(unique = true)
    private String politicalParty;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> voteList;

    private Long totalVotesAttained;

    private Long presidentialVoterIdNumber;

}
