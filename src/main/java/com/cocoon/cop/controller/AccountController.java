package com.cocoon.cop.controller;

import com.cocoon.cop.domain.bank.Account;
import com.cocoon.cop.dto.MemberAccountRegisterDto;
import com.cocoon.cop.request.IncomeRequest;
import com.cocoon.cop.service.AccountService;
import com.cocoon.cop.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/accounts")
public class AccountController {

    private final JWTUtil jwtUtil;

    private final AccountService accountService;

    @PostMapping("/register/{memberId}")
    public ResponseEntity<?> register(@PathVariable("memberId") Long memberId, @RequestBody MemberAccountRegisterDto memberAccountRegisterDto) {
        log.info("memberId = {}", memberId);
        Account registeredAccount = accountService.register(memberId, memberAccountRegisterDto);
        log.info("registeredAccount = {}", registeredAccount);
        return ResponseEntity.ok().body("ok");
    }


    /**
     * 口座残高取得
     */
    @GetMapping("/{account_id}/balance")
    public ResponseEntity<Map<String, Integer>> balance(@PathVariable("account_id") Long accountId) {
        return ResponseEntity.ok().body(Collections.singletonMap("balance", accountService.getBalance(accountId)));
    }


    /**
     * 貯金額取得
     */
    @GetMapping("/{account_id}/saving-balance")
    public ResponseEntity<Map<String, Integer>> savingBalance(@PathVariable("account_id") Long accountId) {
        return ResponseEntity.ok().body(Collections.singletonMap("savingBalance", accountService.getSavingBalance(accountId)));
    }

    /**
     * 貯金
     * Request json body { "amount": 1000 }
     */
    @PostMapping("/{account_Id}/income")
    public ResponseEntity<?> income(@PathVariable("account_Id") Long accountId, @RequestBody IncomeRequest incomeRequest) {

        Map<String, Object> response = new LinkedHashMap<>();
        try {
            accountService.income(accountId, incomeRequest);
            response.put("deposit", incomeRequest.amount());
            response.put("message", "transaction completed");
        } catch (IllegalArgumentException accountNotFound) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "account not found"));
        }
        return ResponseEntity.ok().body(response);
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
