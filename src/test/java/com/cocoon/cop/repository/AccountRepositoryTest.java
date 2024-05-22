package com.cocoon.cop.repository;

import com.cocoon.cop.domain.bank.Account;
import com.cocoon.cop.domain.bank.QAccount;
import com.cocoon.cop.domain.enums.BankStatus;
import com.cocoon.cop.domain.enums.Role;
import com.cocoon.cop.domain.main.Member;
import com.cocoon.cop.domain.main.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static com.cocoon.cop.domain.bank.QAccount.*;
import static com.cocoon.cop.domain.main.QMember.*;

@SpringBootTest
@Transactional
public class AccountRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    JPAQueryFactory queryFactory;

    @Autowired
    EntityManager em;

    @Test
    @Commit
    void insertAccountTest() {
        Member findMember = queryFactory
                .selectFrom(member)
                .fetchOne();

        System.out.println("findMember = " + findMember);

        Account account = new Account(findMember, "2222-3333-4444", 2000);
        em.persist(account);


        Account findAccount = em.find(Account.class, account.getId());
        System.out.println("findAccount.getAccountNumber() = " + findAccount.getAccountNumber());

    }

    @Test
    void membersAccountRegistration() {

        Member member = new Member().builder()
                .name("CHOIJONGWON")
                .email("whddnjs3340@naver.com")
                .image(null)
                .password("hashedPassword")
                .phoneNumber("010-1234-5678")
                .role(Role.ADMIN)
                .build();
        em.persist(member);
        em.flush();
        em.clear();

        Optional<Member> findMember = memberRepository.findById(1L);
        System.out.println("=========== second query =============");

        Account memberAccount = queryFactory
                .select(account)
                .from(QMember.member)
                .where(QMember.member.id.eq(1L))
                .fetchOne();


    }

}