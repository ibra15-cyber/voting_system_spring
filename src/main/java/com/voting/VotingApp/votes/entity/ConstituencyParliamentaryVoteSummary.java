package com.voting.VotingApp.votes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table()
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConstituencyParliamentaryVoteSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String constituencyId; //for each constituency we got 2 entries for diff candidates

    private String districtId;

    private Long parliamentaryCandidateId;

    private Long parliamentaryCandidateVoteTotal;

    //FOR EACH CONSTITUENCY WE NEED TO SEE A SUMMARY OF THE VOTE
    //AKA CONST ID, PRESIDENTIAL CANDIDATE ID
    //CONST 1, JOHN, TOTAL
    //CONST 1,  BAWUMIA, TOTAL

}
