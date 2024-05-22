package com.cocoon.cop.domain.main;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "`ExpRank`")
public class ExpRank {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rank_id")
    private Long id;

    @Column(name = "rank_name")
    private String name;

    @Column(name = "min_experience")
    private int min_experience;

    @Column(name = "max_experience")
    private int max_experience;

}
