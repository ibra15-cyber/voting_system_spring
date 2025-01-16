package com.voting.VotingApp.voting_register.entity;

import com.voting.VotingApp.votes.entity.Vote;
import com.voting.VotingApp.voting_register.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Data
@Table(name="voters")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Voter {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voterId;

    private Long voterNumber;
//
//    @Column(nullable = false)
//    @NotBlank
    private String firstName;

//    @Column(nullable = false)
//    @NotBlank
    private String lastName;

//    @NotBlank
//    @Column(nullable = false)
    private Gender gender;

//    @NotBlank
//    @Column(nullable = false)
    private Integer age;

//    @ManyToOne()
//    @JoinColumn(name="constituency_code")
//    private Constituency constituency;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE) //when a voter is deleted, his vote should be deleted as well
    private Vote vote;

    @ManyToOne()
    @JoinColumn(name="polling_station_electoral_code", referencedColumnName = "pollingStationCode")
    private PollingStation pollingStation;

}
