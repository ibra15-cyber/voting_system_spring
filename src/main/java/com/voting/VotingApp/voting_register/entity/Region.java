package com.voting.VotingApp.voting_register.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.List;

@Table(name="regions")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Region {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regionId;

    @Column(unique = true)
    private String regionName;

    @Column(unique = true)
    private String regionElectoralCode;

    @Column(unique = true)
    private String regionalCapital;

    private Long regionalTotalVotesCast;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval = true )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<District> districts;
}
