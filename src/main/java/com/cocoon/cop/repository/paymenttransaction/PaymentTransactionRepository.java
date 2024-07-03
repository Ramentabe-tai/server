package com.cocoon.cop.repository.paymenttransaction;

import com.cocoon.cop.domain.bank.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long>, PaymentTransactionRepositoryCustom {
}
