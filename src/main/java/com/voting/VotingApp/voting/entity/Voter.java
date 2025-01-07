package com.voting.VotingApp.voting.entity;

import com.voting.VotingApp.voting.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

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
    private int age;

    @ManyToOne()
    @JoinColumn(name="constituencyId")
    private Constituency constituency;

    @OneToOne(mappedBy = "voter")
    private Vote vote;

}
