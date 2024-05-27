package com.cocoon.cop.service;

import com.cocoon.cop.domain.bank.Account;
import com.cocoon.cop.domain.main.Member;
import com.cocoon.cop.dto.MemberAccountRegisterDto;
import com.cocoon.cop.repository.AccountRepository;
import com.cocoon.cop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final MemberRepository memberRepository;


    public Account register(Long memberId, MemberAccountRegisterDto memberAccountRegisterDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Account account = new Account(member, memberAccountRegisterDto.getAccountNumber());
        member.setAccount(account);
        return accountRepository.save(account);
    }
}
