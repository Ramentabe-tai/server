package com.cocoon.cop.repository;

import com.cocoon.cop.domain.enums.Role;
import com.cocoon.cop.domain.main.Image;
import com.cocoon.cop.domain.main.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@SpringBootTest
@Transactional
class MemberRepositoryTest {


    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;


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





}