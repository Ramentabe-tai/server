package com.cocoon.cop.repository;

import com.cocoon.cop.domain.bank.QAccount;
import com.cocoon.cop.domain.enums.Role;
import com.cocoon.cop.domain.main.Image;
import com.cocoon.cop.domain.main.Member;
import com.cocoon.cop.domain.main.QImage;
import com.cocoon.cop.domain.main.QMember;
import com.cocoon.cop.dto.MemberDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.cocoon.cop.domain.bank.QAccount.*;
import static com.cocoon.cop.domain.main.QImage.*;
import static com.cocoon.cop.domain.main.QMember.*;


@SpringBootTest
@Transactional
class MemberRepositoryTest {


    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberRepositoryTest.class);


    @Test
    @Commit
    void insertTestMembers() {


        Member member = new Member().builder()
                .name("CHOIJONGWON")
                .email("whddnjs3340@naver.com")
                .image(null)
                .password("hashedPassword")
                .phoneNumber("010-1234-5678")
                .role(Role.ADMIN)
                .build();

        memberRepository.save(member);

        Optional<Member> findMember = memberRepository.findById(member.getId());
        String findMemberName = findMember
                .orElseThrow(() -> new IllegalArgumentException("Member not found"))
                .getName();

        System.out.println("findMemberName = " + findMemberName);
    }

    /**
     * QueryDSLのProjectionを利用してMember EntityをMemberDtoに変換
     */
    @Test
    void memberToMemberDto() {

        List<MemberDto> memberDtoList = jpaQueryFactory
                .select(
                        Projections.fields(MemberDto.class,
                                member.name.as("name"),
                                member.email.as("email"),
                                member.role.as("role"),
                                member.phoneNumber.as("phoneNumber"),
                                member.rankPoint.as("rankPoint"),
                                member.account.id.as("accountId"),
                                member.image.id.as("profileImageId")
                        )
                )
                .from(member)
                .leftJoin(member.account, account)
                .leftJoin(member.image, image)
                .fetch();

        LOGGER.info("memberDtoList = {}", memberDtoList);

    }





}