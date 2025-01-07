package com.voting.VotingApp.voting.entity;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private int age;

    @ManyToOne()
    @JoinColumn(name="constituencyId")
    private Constituency constituency;

    @OneToOne(mappedBy = "voter")
    private Vote vote;

}
