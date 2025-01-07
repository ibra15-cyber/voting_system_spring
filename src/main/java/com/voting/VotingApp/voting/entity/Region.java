package com.voting.VotingApp.voting.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Table(name="Regions")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Region {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regionId;

    @Column(unique = true)
    private String regionName;

    private BigDecimal regionalTotalVotesCast;

    private BigDecimal getRegionalTotalValidVotes;

    private BigDecimal getRegionalTotalSpoiltVotes;


    private BigDecimal regionalTotalForAPresidentialCandidate;

    private BigDecimal regionalTotalForAParliamentaryCandidate;

    @OneToMany(mappedBy = "region")
    private List<District> districts;
}
