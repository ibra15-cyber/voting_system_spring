package com.voting.VotingApp.voting_register.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Table(name="districts")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long districtId;
    private String districtName;

    @Column(unique = true)
    private String districtElectoralCode;

    @Column(unique = true)
    private String districtCapital;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Constituency> constituency;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    private Long districtTotalVotesCast;

}
