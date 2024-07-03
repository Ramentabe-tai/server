package com.cocoon.cop.domain.main;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Mission {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(name = "mission_title", length = 50)
    private String title;

    @Column(name = "mission_description", length = 100)
    private String description;

    @Column(name = "exp_point")
    private int expPoint;

}
