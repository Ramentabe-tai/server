package com.cocoon.cop.repository.member;

import com.cocoon.cop.dto.MemberDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import static com.cocoon.cop.domain.main.QMember.*;

@Repository
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    public MemberDto findByIdToDto(Long memberId) {
        return queryFactory
                .select(
                        Projections.fields(MemberDto.class,
                                member.id,
                                member.name,
                                member.ruby,
                                member.email,
                                member.phoneNumber,
                                member.rankPoint,
                                member.account.id.as("accountId")
                        )
                ).from(member)
                .where(member.id.eq(memberId))
                .fetchOne();
    }
}
