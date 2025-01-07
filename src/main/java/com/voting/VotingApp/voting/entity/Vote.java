package com.voting.VotingApp.voting.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="votes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @OneToOne()
    @JoinColumn(name="voter")
    private Voter voter;

    @ManyToOne()
    @JoinColumn(name = "parliamentary_candidate")
    private ConstituencyCandidate constituencyCandidate;

    @ManyToOne()
    @JoinColumn(name = "presidential_candidate")
    private PresidentialCandidate presidentialCandidate;

}
