package com.cocoon.cop.repository;

import com.cocoon.cop.domain.main.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

//    @Test
//    public void insertTestMember()  {
////        Member createmember = new Member("CHOI JONGWON", "whddnjs3340@naver.com", Role.ADMIN);
////        em.persist(createmember);
//
//        Optional<Member> findMember = memberRepository.findMemberByEmail("whddnjs3340@naver.com"); //  1차 캐시
////        findMember.orElseThrow(NullPointerException::new);
//    }
//
//    @Test
//    public void testMemberRepository() {
//        Optional<Member> findMember = memberRepository.findMemberByEmail("whddnjs3340@naver.com");
//        if(!findMember.isPresent()) {
//            System.out.println("okay");
//        }
//    }


}