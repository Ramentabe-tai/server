package com.cocoon.cop.service;

import com.cocoon.cop.domain.enums.TransactionType;
import com.cocoon.cop.dto.PaymentTransactionDto;
import com.cocoon.cop.repository.paymenttransaction.PaymentTransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentTransactionService {

    private final PaymentTransactionRepository paymentTransactionRepository;


    public List<PaymentTransactionDto> getTransactions(Long accountId, String type) {

        TransactionType transactionType = TransactionType.valueOf(type.toUpperCase());
        return paymentTransactionRepository.transactions(accountId, transactionType);
    }
}
