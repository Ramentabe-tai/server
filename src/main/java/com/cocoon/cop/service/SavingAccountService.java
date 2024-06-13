package com.cocoon.cop.service;

import com.cocoon.cop.domain.bank.PaymentTransaction;
import com.cocoon.cop.domain.enums.TransactionType;
import com.cocoon.cop.domain.main.Member;
import com.cocoon.cop.repository.account.AccountRepository;
import com.cocoon.cop.repository.member.MemberRepository;
import com.cocoon.cop.repository.PaymentTransactionRepository;
import com.cocoon.cop.repository.savingaccount.SavingAccountRepository;
import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Slf4j
@Service
public class SavingAccountService {

    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;
    private final SavingAccountRepository savingAccountRepository;
    private final PaymentTransactionRepository paymentTransactionRepository;

    @Transactional(rollbackOn = Exception.class)
    public void income(Long memberId, Long accountId, int amount) {

        accountRepository.expense(accountId, amount); // 個人用口座からの出金, 貯金用口座に入金するため、カテゴリーはnullで設定
        savingAccountRepository.income(memberId, amount); // 貯金口座への入金

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("member not found"));

        // 貯金口座への入金履歴を登録。個人用口座への入金は管理しないこと
        PaymentTransaction paymentTransaction = new PaymentTransaction().builder()
                .category(null)
                .member(member)
                .amount(amount)
                .transactionType(TransactionType.DEPOSIT)
                .transactionDate(LocalDateTime.now())
                .build();

        paymentTransactionRepository.save(paymentTransaction);
    }
}
