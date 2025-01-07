package com.voting.VotingApp.voting.entity;

import com.voting.VotingApp.voting.enums.PoliticalParty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private PoliticalParty politicalParty;

    @OneToMany(mappedBy = "presidentialCandidate")
    private List<Vote> votes;
}
