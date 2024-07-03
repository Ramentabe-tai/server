package com.cocoon.cop.repository.paymenttransaction;

import com.cocoon.cop.domain.enums.TransactionType;
import com.cocoon.cop.dto.PaymentTransactionDto;

import java.util.List;

public interface PaymentTransactionRepositoryCustom {

    List<PaymentTransactionDto> transactions(Long accountId, TransactionType transactionType);

}
