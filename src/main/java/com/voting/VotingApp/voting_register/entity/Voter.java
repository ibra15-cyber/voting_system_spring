package com.voting.VotingApp.voting_register.entity;

import com.voting.VotingApp.votes.entity.Vote;
import com.voting.VotingApp.voting_register.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

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

    private String voterNumber;
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

    @ManyToOne()
    @JoinColumn(name="constituency_id")
    private Constituency constituency;

    @OneToOne
    private Vote vote;

}
