package com.cocoon.cop.controller;

import com.cocoon.cop.domain.bank.Account;
import com.cocoon.cop.dto.MemberAccountRegisterDto;
import com.cocoon.cop.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/register/{memberId}")
    public ResponseEntity<?> register(@PathVariable("memberId") Long memberId, @RequestBody MemberAccountRegisterDto memberAccountRegisterDto) {
        log.info("memberId = {}", memberId);
        Account registeredAccount = accountService.register(memberId, memberAccountRegisterDto);
        log.info("registeredAccount = {}", registeredAccount);
        return ResponseEntity.ok().body("ok");
    }

}
