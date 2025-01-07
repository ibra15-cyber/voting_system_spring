package com.voting.VotingApp.voting.entity;

import com.voting.VotingApp.voting.enums.PoliticalParty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

//    @Column(nullable = false)
//    @NotBlank
    private String firstName;

//    @Column(nullable = false)
//    @NotBlank
    private String lastName;

//    @Column(nullable = false)
//    @NotBlank
    private String gender;

//    @Column(nullable = false)
//    @NotBlank
    private int age;

//    @Column(nullable = false)
//    @NotBlank
    private PoliticalParty politicalParty;

    @OneToMany(mappedBy = "constituencyCandidate")
    private List<Vote> votes;
}
