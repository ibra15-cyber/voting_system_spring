package com.voting.VotingApp.votes.entity;

import com.voting.VotingApp.voting_register.entity.*;
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
    @OneToOne()
    @JoinColumn(name = "voter_id_number", referencedColumnName = "voterNumber")
    private Voter voter;

    //the parliamentary and presidential candidate voted for
    @ManyToOne
    @JoinColumn(name="parlimentary_Candidate_id", referencedColumnName = "parliamentaryCandidateNumber")
    private ParliamentaryCandidate parliamentaryCandidate;

    @ManyToOne
    @JoinColumn(name="presidentialCandidate_id", referencedColumnName = "presidentialVoterIdNumber")
    private PresidentialCandidate presidentialCandidate;

    @ManyToOne
    @JoinColumn(name = "polling_electoral_code", referencedColumnName = "pollingStationCode")
    private PollingStation pollingStation;

    @Column(nullable = false)
    private String constituencyCode;

    @Column(nullable = false)
    private String regionalCode;

}

//just like we have orderItem, and orders, we shd have votes and voterItem aka presidentialCandidate
//but the link btw product and order is orderItem
//therefore the link between vote (n) and candidate  is ? so a valid vote, vote cast breaks the tie
//
//    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<VoteCast> voteCastList;

//    @ManyToOne
//    @JoinColumn(name = "constituency-electoral-code", referencedColumnName = "constituencyElectoralCode")
//    private Constituency constituency; //will be pulled from the parliamentary candidate







