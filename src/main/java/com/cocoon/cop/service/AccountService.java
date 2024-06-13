package com.cocoon.cop.service;

import com.cocoon.cop.domain.bank.Account;
import com.cocoon.cop.domain.bank.PaymentTransaction;
import com.cocoon.cop.domain.enums.TransactionType;
import com.cocoon.cop.domain.main.Category;
import com.cocoon.cop.domain.main.Member;
import com.cocoon.cop.dto.MemberAccountRegisterDto;
import com.cocoon.cop.repository.account.AccountRepository;
import com.cocoon.cop.repository.category.CategoryRepository;
import com.cocoon.cop.repository.member.MemberRepository;
import com.cocoon.cop.repository.savingaccount.SavingAccountRepository;
import com.cocoon.cop.repository.PaymentTransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final PaymentTransactionRepository transactionRepository;
    private final PaymentTransactionRepository paymentTransactionRepository;


    public Account register(Long memberId, MemberAccountRegisterDto memberAccountRegisterDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Account account = new Account(member, memberAccountRegisterDto.getAccountNumber(), 0);
        member.setAccount(account);
        return accountRepository.save(account);
    }

    public int balance(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        return account.getBalance();
    }

    @Transactional(rollbackOn = Exception.class)
    public void expenseWithCategory(Long memberId, Long accountId, int amount, String categoryName) {

        Category category = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        accountRepository.expense(accountId, amount);
        PaymentTransaction paymentTransaction = new PaymentTransaction().builder()
                .member(member)
                .category(category)
                .transactionType(TransactionType.WITHDRAWAL)
                .amount(amount)
                .transactionDate(LocalDateTime.now())
                .build();

        paymentTransactionRepository.save(paymentTransaction);

    }
}
