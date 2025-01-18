package com.voting.VotingApp.voting_register.entity;


import com.voting.VotingApp.votes.entity.Vote;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    private String constituencyCapital;

    private Long constituencyTotalVotesCast;

    @ManyToOne()
    @JoinColumn(name = "district_electoral_code", referencedColumnName = "districtElectoralCode") // Mapping by code
    private District district;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ParliamentaryCandidate> parliamentaryCandidates;

    @OneToMany(mappedBy = "constituency", cascade = CascadeType.ALL,orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PollingStation> listOfPollingStations;

//    @OneToMany(mappedBy = "constituency", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Vote>  vote;


//    @OneToMany(mappedBy = "constituency", cascade = CascadeType.ALL, orphanRemoval = true)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private List<Voter> voter;

}
