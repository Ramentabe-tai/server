package com.cocoon.cop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
public class MemberMissionDto {
    private Long missionId;
    private String missionTitle;
    private String missionDescription;
    private int expPoint;
    private Boolean isCompleted;
    private LocalDateTime completedDate;

    public MemberMissionDto(Long missionId, String missionTitle, String missionDescription, int expPoint, Boolean isCompleted, LocalDateTime completedDate) {
        this.missionId = missionId;
        this.missionTitle = missionTitle;
        this.missionDescription = missionDescription;
        this.expPoint = expPoint;
        this.isCompleted = isCompleted;
        this.completedDate = completedDate;
    }
}

