package com.voting.VotingApp.voting.entity;


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

    private BigDecimal constituencyTotalVotes;

    private BigDecimal constituencyTotalValidVotes;

    private BigDecimal constituencyTotalInvalidVotes;

    private BigDecimal constituencyTotalForAPresidentialCandidate;

    private BigDecimal constituencyTotalForAParliamentaryCandidate;

    @ManyToOne
    @JoinColumn(name = "district_id" )
    private District district;

    @OneToMany(mappedBy = "constituency")
    private List<Voter> voter;




}
