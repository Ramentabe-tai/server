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
import com.cocoon.cop.repository.paymenttransaction.PaymentTransactionRepository;
import com.cocoon.cop.request.ExpenseRequest;
import com.cocoon.cop.request.IncomeRequest;
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
    private final PaymentTransactionRepository paymentTransactionRepository;


    public Account register(Long memberId, MemberAccountRegisterDto memberAccountRegisterDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Account account = new Account(member, memberAccountRegisterDto.getAccountNumber(), 0);
        member.setAccount(account);
        return accountRepository.save(account);
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

    public int getBalance(Long accountId) {
        return accountRepository.balance(accountId);
    }

    public Integer getSavingBalance(Long accountId) {
        return accountRepository.savingBalance(accountId);
    }

    @Transactional(rollbackOn = IllegalArgumentException.class)
    public int income(Long accountId, IncomeRequest incomeRequest) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));


        int amount = incomeRequest.amount();
        int balance = account.addBalance(amount); // Dirty Checking
        log.info("balance = {}", balance);

        // 돈 적금하고, PaymentTransaction 에 이력저장
        PaymentTransaction paymentTransaction = PaymentTransaction.builder()
                .member(account.getMember())
                .account(account)
                .amount(amount)
                .transactionType(TransactionType.DEPOSIT)
                .message(incomeRequest.memo())
                .transactionDate(LocalDateTime.now())
                .build();

        paymentTransactionRepository.save(paymentTransaction);

        return balance;
    }

    @Transactional(rollbackOn = IllegalArgumentException.class)
    public int expense(Long accountId, ExpenseRequest expenseRequest) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        Category category = categoryRepository.findById(expenseRequest.categoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        int amount = expenseRequest.amount();
        int balance = account.subtractBalance(amount); // Dirty Checking
        log.info("balance = {}", balance);

        // PaymentTransaction 에 이력 저장
        PaymentTransaction paymentTransaction = PaymentTransaction.builder()
                .member(account.getMember())
                .account(account)
                .category(category)
                .amount(amount)
                .transactionType(TransactionType.WITHDRAWAL)
                .message(expenseRequest.memo())
                .transactionDate(LocalDateTime.now())
                .build();

        paymentTransactionRepository.save(paymentTransaction);

        return balance;
    }
}
