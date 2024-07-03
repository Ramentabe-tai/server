package com.cocoon.cop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DepositPaymentTransactionDto extends PaymentTransactionDto {
    private int deposit;
}
