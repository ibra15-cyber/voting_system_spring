package com.voting.VotingApp.votes.entity;

import com.voting.VotingApp.voting_register.entity.Constituency;
import com.voting.VotingApp.voting_register.entity.ParliamentaryCandidate;
import com.voting.VotingApp.voting_register.entity.PresidentialCandidate;
import com.voting.VotingApp.voting_register.entity.Voter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "votes")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    //we could add voterNumber but will lead to redundancy
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(unique = true)
    private Voter voter;

    //the parliamentary and presidential candidate voted for
    @ManyToOne
    @JoinColumn(name="parlimentaryCandidate_id")
    private ParliamentaryCandidate parliamentaryCandidate;

    @ManyToOne
    @JoinColumn(name="presidentialCandidate_id")
    private PresidentialCandidate presidentialCandidate;

    private Long constituencyId; //will be pulled from the parliamentary candidate

    private String pollingStationId;

    //just like we have orderItem, and orders, we shd have votes and voterItem aka presidentialCandidate
    //but the link btw product and order is orderItem
    //therefore the link between vote (n) and candidate  is ? so a valid vote, vote cast breaks the tie
//
//    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<VoteCast> voteCastList;









}
