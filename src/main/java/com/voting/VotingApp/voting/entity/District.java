package com.voting.VotingApp.voting.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name="districts")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long districtId;
    private String districtName;

    @OneToMany(mappedBy = "district")
    private List<Constituency> constituency;

    @ManyToOne
    @JoinColumn(name = "region")
    private Region region;
}
