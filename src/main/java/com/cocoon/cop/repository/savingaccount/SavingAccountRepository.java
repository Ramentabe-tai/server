package com.cocoon.cop.repository.savingaccount;

import com.cocoon.cop.domain.bank.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingAccountRepository extends JpaRepository<SavingAccount, Long>, SavingAccountRepositoryCustom {

}
