package com.cocoon.cop.repository;

import com.cocoon.cop.dto.MemberDto;
import com.cocoon.cop.repository.member.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


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

//
//    @Test
//    @Commit
//    void insertTestMembers() {
//
//
//        Member member = new Member().builder()
//                .name("CHOIJONGWON")
//                .email("whddnjs3340@naver.com")
//                .image(null)
//                .password("hashedPassword")
//                .phoneNumber("010-1234-5678")
//                .role(Role.ADMIN)
//                .build();
//
//        memberRepository.save(member);
//
//        Optional<Member> findMember = memberRepository.findById(member.getId());
//        String findMemberName = findMember
//                .orElseThrow(() -> new IllegalArgumentException("Member not found"))
//                .getName();
//
//        System.out.println("findMemberName = " + findMemberName);
//    }

    /**
     * QueryDSLのProjectionを利用してMember EntityをMemberDtoに変換
     */
//    @Test
//    void memberToMemberDto() {
//
//        List<MemberDto> memberDtoList = jpaQueryFactory
//                .select(
//                        Projections.fields(MemberDto.class,
//                                member.name.as("name"),
//                                member.email.as("email"),
//                                member.role.as("role"),
//                                member.phoneNumber.as("phoneNumber"),
//                                member.rankPoint.as("rankPoint"),
//                                member.account.id.as("accountId"),
//                                member.image.id.as("profileImageId")
//                        )
//                )
//                .from(member)
//                .leftJoin(member.account, account)
//                .leftJoin(member.image, image)
//                .fetch();
//
//        LOGGER.info("memberDtoList = {}", memberDtoList);
//
//    }


    @Test
    void findByIdToDto() {
        MemberDto memberDto = memberRepository.findByIdToDto(1L);
        LOGGER.info("memberDto = {}", memberDto);
    }





}