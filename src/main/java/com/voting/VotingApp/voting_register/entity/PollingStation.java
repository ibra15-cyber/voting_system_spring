package com.voting.VotingApp.voting_register.entity;


import com.voting.VotingApp.votes.entity.Vote;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Data
@Table(name="polling_stations")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PollingStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pollingStationId;

    @Column(unique = true)
    private String pollingStationCode;

    @Column(unique = true)
    private String pollingStationName;

    @ManyToOne
    @JoinColumn(name = "constituency_electoral_code", referencedColumnName = "constituencyElectoralCode")
    private Constituency constituency;

    private Long totalVoteCastAtPollingStation;

    @OneToMany(mappedBy = "pollingStation", cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Voter> voters;

    @OneToMany(mappedBy = "pollingStation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> vote;

}
