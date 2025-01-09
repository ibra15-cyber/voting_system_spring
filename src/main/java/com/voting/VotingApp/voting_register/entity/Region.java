package com.voting.VotingApp.voting_register.entity;

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

    @Column(unique = true)
    private String regionElectoralCode;

    @Column(unique = true)
    private String regionalCapital;

    private BigDecimal regionalTotalVotesCast;

    private BigDecimal regionalTotalForAPresidentialCandidate;

    private BigDecimal regionalTotalForAParliamentaryCandidate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<District> districts;
}
