package com.cocoon.cop.controller;

import com.cocoon.cop.domain.bank.Account;
import com.cocoon.cop.dto.*;
import com.cocoon.cop.request.ExpenseRequest;
import com.cocoon.cop.request.IncomeRequest;
import com.cocoon.cop.service.AccountService;
import com.cocoon.cop.service.PaymentTransactionService;
import com.cocoon.cop.utils.JWTUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/accounts")
public class AccountController {

    private final JWTUtil jwtUtil;

    private final AccountService accountService;
    private final PaymentTransactionService paymentTransactionService;

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
            return ResponseEntity.badRequest().body(response.put("message", "account not found"));
        }
        return ResponseEntity.ok().body(response);
    }



    @PostMapping("/{account_Id}/expense")
    public ResponseEntity<?> expense(@PathVariable("account_Id") Long accountId, @RequestBody ExpenseRequest expenseRequest) {

        Map<String, Object> response = new LinkedHashMap<>();

        try {
            accountService.expense(accountId, expenseRequest);
            response.put("withdrawal", expenseRequest.amount());
            response.put("message", "transaction completed");
        } catch (IllegalArgumentException accountNotFound) {
            return ResponseEntity.badRequest().body(response.put("message", "account not found"));
        }

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{account_Id}/transactions")
    public ResponseEntity<Map<String, Object>> transactions(@PathVariable("account_Id") Long accountId, @RequestParam("type") String type) {
        type = type.toUpperCase();
        List<PaymentTransactionDto> transactions = paymentTransactionService.getTransactions(accountId, type);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("data", transformToDynamicResponse(transactions, type));

        return ResponseEntity.ok().body(response);
    }

    private List<Map<String, Object>> transformToDynamicResponse(List<PaymentTransactionDto> transactions, String type) {
        return transactions.stream().map(transaction -> {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("month", transaction.getMonth());
            map.put(transaction.getFieldName(type), transaction.getAmount());
            return map;
        }).toList();
    }

}
