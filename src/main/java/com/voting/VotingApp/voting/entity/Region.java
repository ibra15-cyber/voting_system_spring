package com.voting.VotingApp.voting.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name="Regions")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Region {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regionId;
    private String regionName;

    @OneToMany(mappedBy = "region")
    private List<District> districts;
}
