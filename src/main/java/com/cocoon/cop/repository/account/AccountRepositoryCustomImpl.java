package com.cocoon.cop.repository.account;

import com.cocoon.cop.domain.bank.Account;
import com.cocoon.cop.domain.main.Category;
import com.cocoon.cop.repository.category.CategoryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import static com.cocoon.cop.domain.bank.QAccount.*;

@Repository
public class AccountRepositoryCustomImpl implements AccountRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final CategoryRepository categoryRepository;

    public AccountRepositoryCustomImpl(EntityManager em, CategoryRepository categoryRepository) {
        this.queryFactory = new JPAQueryFactory(em);
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Long findMemberIdByAccountId(Long accountId) {
        Long memberId = queryFactory
                .select(account.member.id)
                .from(account)
                .where(account.id.eq(accountId))
                .fetchOne();
        return 1L;
    }

    @Override
    public int expense(Long accountId, int amount) {
        Account memberAccount = queryFactory
                .selectFrom(account)
                .where(account.id.eq(accountId))
                .fetchOne();

//        assert memberAccount != null;
        return memberAccount.subtractBalance(amount);
    }

    @Override
    public int balance(Long accountId) {
        return queryFactory
                .select(account.balance)
                .from(account)
                .where(account.id.eq(accountId))
                .fetchOne().intValue();
    }

    @Override
    public int savingBalance(Long accountId) {
        return queryFactory
                .select(account.savingBalance)
                .from(account)
                .where(account.id.eq(accountId))
                .fetchOne().intValue();
    }

}
