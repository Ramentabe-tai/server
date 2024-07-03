package com.cocoon.cop.domain.main;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class MemberMission {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", referencedColumnName = "mission_id")
    private Mission mission;

    @Column(name = "is_completed", columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean isCompleted;

    @Column(name = "completed_date")
    private LocalDateTime completedDate;

    @Builder
    public MemberMission(Member member, Mission mission, Boolean isCompleted, LocalDateTime completedDate) {
        this.member = member;
        this.mission = mission;
        this.isCompleted = isCompleted;
        this.completedDate = completedDate;
    }
}
