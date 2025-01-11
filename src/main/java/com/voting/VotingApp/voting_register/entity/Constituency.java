package com.voting.VotingApp.voting_register.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Table(name="constituencies")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Constituency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long constituencyId;

    @Column(unique = true)
    private String constituencyName;

    @Column(unique = true)
    private String constituencyElectoralCode;

    @Column(unique = true)
    private String constituencyCapital;

    private Long constituencyTotalVotesCast;

    @ManyToOne
    @JoinColumn(name = "district_id" )
    private District district;

    @OneToMany()
    private List<Voter> voter;

    @OneToMany()
    private List<ParliamentaryCandidate> parliamentaryCandidates;

}
