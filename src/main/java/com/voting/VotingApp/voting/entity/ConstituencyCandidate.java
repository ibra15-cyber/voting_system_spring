package com.voting.VotingApp.voting.entity;

import com.voting.VotingApp.voting.enums.PoliticalParty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name="parliamentary_candidate")
@NoArgsConstructor
@AllArgsConstructor
public class ConstituencyCandidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parliamentaryCandidateId;
    private String firstName;
    private String lastName;

    private String gender;
    private int age;

    private PoliticalParty politicalParty;

    @OneToMany(mappedBy = "constituencyCandidate")
    private List<Vote> votes;
}
