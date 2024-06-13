package com.cocoon.cop.repository.savingaccount;

import com.cocoon.cop.domain.bank.QSavingAccount;
import com.cocoon.cop.domain.bank.SavingAccount;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static com.cocoon.cop.domain.bank.QSavingAccount.*;

@Repository
@Slf4j
public class SavingAccountRepositoryCustomImpl implements SavingAccountRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public SavingAccountRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public int income(Long memberId, int amount) {
        SavingAccount memberSavingAccount = queryFactory
                .selectFrom(savingAccount)
                .where(savingAccount.member.id.eq(memberId))
                .fetchOne();

        log.info("memberSavingAccount = {}", memberSavingAccount);

        return memberSavingAccount.addBalance(amount);
    }
}
