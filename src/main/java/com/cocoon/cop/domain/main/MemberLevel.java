package com.cocoon.cop.domain.main;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class MemberLevel {
    @Id
    private Long memberId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "exp_points", columnDefinition = "INT NOT NULL DEFAULT 0")
    private int expPoints;

    @Column(name = "level", columnDefinition = "INT NOT NULL DEFAULT 1")
    private int level;

}
