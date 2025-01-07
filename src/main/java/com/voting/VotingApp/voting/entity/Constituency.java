package com.voting.VotingApp.voting.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Table(name="constituencies")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Constituency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long constituencyId;
    private String constituencyName;

    @ManyToOne
    @JoinColumn(name = "district" )
    private District district;

    @OneToMany(mappedBy = "constituency")
    private List<Voter> voter;


}
