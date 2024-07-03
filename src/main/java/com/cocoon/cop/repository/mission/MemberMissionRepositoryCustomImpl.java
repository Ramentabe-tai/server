package com.cocoon.cop.repository.mission;

import com.cocoon.cop.dto.MemberMissionDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.cocoon.cop.domain.main.QMember.member;
import static com.cocoon.cop.domain.main.QMemberMission.memberMission;
import static com.cocoon.cop.domain.main.QMission.mission;

public class MemberMissionRepositoryCustomImpl implements MemberMissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberMissionRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }




    @Override
    public List<MemberMissionDto> findAllMissionsForMember(Long memberId) {
        return queryFactory.select(Projections.constructor(MemberMissionDto.class,
                        mission.id,
                        mission.title,
                        mission.description,
                        mission.expPoint,
                        memberMission.isCompleted,
                        memberMission.completedDate
                ))
                .from(mission)
                .leftJoin(memberMission).on(memberMission.mission.id.eq(mission.id).and(memberMission.member.id.eq(memberId)))
                .fetch();
    }
}
