package com.voting.VotingApp.voting_register.entity;

import com.voting.VotingApp.voting_register.enums.PoliticalParty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Table(name="parliamentary_candidates")
@NoArgsConstructor
@AllArgsConstructor
public class ParliamentaryCandidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne
    @JoinColumn(name = "constituency_id")
    private Constituency constituency;


    private BigDecimal totalVotesAttained;

}
