package com.cocoon.cop.repository.account;


public interface AccountRepositoryCustom {

    Long findMemberIdByAccountId(Long accountId);
    int expense(Long accountId, int amount);

    int balance(Long accountId);

    int savingBalance(Long accountId);

}
