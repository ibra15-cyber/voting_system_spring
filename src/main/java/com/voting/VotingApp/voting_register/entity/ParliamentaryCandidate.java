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
    private Integer age;

//    @Column(nullable = false)
//    @NotBlank
    private String politicalParty;

    @OneToMany(mappedBy = "parliamentaryCandidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> voteList;

    @ManyToOne
    @JoinColumn(name = "constituency_electoral_code", referencedColumnName = "constituencyElectoralCode")
    private Constituency constituency;


    private Long totalVotesAttained;

    //I could have a relationship between voter and parliamentary candidate, but I choose not be because, there is negligible no of voters that will be contesting
    //I choose to add a denomalized property to this entity to take the voters id number, which is even simpler.
    private Long parliamentaryCandidateNumber;

}
