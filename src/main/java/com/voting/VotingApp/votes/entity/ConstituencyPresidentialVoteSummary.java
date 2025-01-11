package com.voting.VotingApp.votes.entity;

import com.voting.VotingApp.voting_register.enums.PoliticalParty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table()
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConstituencyPresidentialVoteSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long constituencyId; //for each constituency we got 2 entries for diff candidates

    private Long districtId;

    private Long presidentialCandidateId;

    private Long presidentialCandidateVoteTotal;

    //FOR EACH CONSTITUENCY WE NEED TO SEE A SUMMARY OF THE VOTE
    //AKA CONST ID, PRESIDENTIAL CANDIDATE ID
    //CONST 1, JOHN, TOTAL
    //CONST 1,  BAWUMIA, TOTAL

}
