package com.cocoon.cop.repository.paymenttransaction;

import com.cocoon.cop.domain.enums.TransactionType;
import com.cocoon.cop.dto.PaymentTransactionDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.cocoon.cop.domain.bank.QPaymentTransaction.paymentTransaction;
import static org.springframework.boot.origin.Origin.from;

public class PaymentTransactionRepositoryCustomImpl implements PaymentTransactionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PaymentTransactionRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<PaymentTransactionDto> transactions(Long accountId, TransactionType transactionType) {
        return queryFactory
                .select(
                        Projections.constructor(
                                PaymentTransactionDto.class,
                                Expressions.stringTemplate(
                                        "DATE_FORMAT({0}, '%Y-%m')", paymentTransaction.transactionDate).as("month"),
                                paymentTransaction.amount.sum().as("amount")
                        ))
                .from(paymentTransaction)
                .where(paymentTransaction.transactionType.eq(transactionType)
                        .and(paymentTransaction.account.id.eq(accountId)))
                .groupBy(Expressions.stringTemplate(
                        "DATE_FORMAT({0}, '%Y-%m')", paymentTransaction.transactionDate))
                .fetch();
    }
}
