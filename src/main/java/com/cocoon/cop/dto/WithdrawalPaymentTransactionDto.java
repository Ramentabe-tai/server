package com.cocoon.cop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WithdrawalPaymentTransactionDto extends PaymentTransactionDto {

    private int spending;
}
