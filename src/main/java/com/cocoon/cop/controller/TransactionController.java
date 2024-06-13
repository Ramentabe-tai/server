package com.cocoon.cop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/transactions")
public class TransactionController {

    @GetMapping("/{account_id}/history")
    public ResponseEntity<?> history(@PathVariable("account_id") Long accountId) {
        return ResponseEntity.ok().body("ok");
    }

}
