package com.cocoon.cop.controller;

import com.cocoon.cop.domain.bank.Account;
import com.cocoon.cop.dto.MemberAccountRegisterDto;
import com.cocoon.cop.service.AccountService;
import com.cocoon.cop.service.SavingAccountService;
import com.cocoon.cop.utils.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/accounts")
public class AccountController {

    private final JWTUtil jwtUtil;

    private final AccountService accountService;
    private final SavingAccountService savingAccountService;

    @PostMapping("/register/{memberId}")
    public ResponseEntity<?> register(@PathVariable("memberId") Long memberId, @RequestBody MemberAccountRegisterDto memberAccountRegisterDto) {
        log.info("memberId = {}", memberId);
        Account registeredAccount = accountService.register(memberId, memberAccountRegisterDto);
        log.info("registeredAccount = {}", registeredAccount);
        return ResponseEntity.ok().body("ok");
    }

    @GetMapping("/{account_Id}/balance")
    public ResponseEntity<?> balance(@PathVariable("account_Id") Long accountId, HttpServletRequest request,
                                     @RequestHeader("Authorization") String authorizationHeader) {

        return ResponseEntity.ok().body(Collections.singletonMap("balance", accountService.balance(accountId)));
    }

    @PostMapping("/{account_Id}/income")
    public ResponseEntity<?> income(@PathVariable("account_Id") Long accountId, @RequestParam("amount") int amount,
                                    @RequestHeader("Authorization") String authorizationHeader) {

        String jwtToken = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7);
            log.info("jwtToken = {}", jwtToken);
        }

        Long memberId = jwtUtil.getId(jwtToken);


        savingAccountService.income(memberId, accountId, amount);

        return ResponseEntity.ok().body(Collections.singletonMap("message", "income success"));
    }

    @PostMapping("/{account_Id}/expense")
    public ResponseEntity<?> expense(@PathVariable("account_Id") Long accountId, @RequestParam("amount") int amount,
                                     @RequestParam("category") String category,
                                     @RequestHeader("Authorization") String authorizationHeader) {

        String jwtToken = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7);
        }

        Long memberId = jwtUtil.getId(jwtToken);

        accountService.expenseWithCategory(memberId, accountId, amount, category);

        return ResponseEntity.ok().body(Collections.singletonMap("message", "expense success"));
    }
}
