package com.cocoon.cop.repository.savingaccount;

import com.cocoon.cop.domain.bank.SavingAccount;
import com.cocoon.cop.domain.main.Member;
import com.cocoon.cop.repository.member.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.cocoon.cop.domain.bank.QSavingAccount.savingAccount;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SavingAccountRepositoryCustomImplTest {

    @Autowired
    SavingAccountRepository savingAccountRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    JPAQueryFactory queryFactory;

    @BeforeEach
    void 저축용계좌Set() {
        Member findMember = memberRepository.findById(2L)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        SavingAccount savingAccount = new SavingAccount().builder()
                .member(findMember)
                .balance(0)
                .savingAccountNumber("79382551")
                .createdDate(LocalDateTime.now())
                .build();

        SavingAccount savedAccount = savingAccountRepository.save(savingAccount);
        System.out.println("savedAccount = " + savedAccount);
    }
    @Test
    @DisplayName("저축용 계좌 입금 테스트")
    void 저축용계좌입금테스트() {
        int incomeMoney = savingAccountRepository.income(2L, 20000);
        System.out.println("incomeMoney = " + incomeMoney);
    }

}