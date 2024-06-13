package com.cocoon.cop.repository.account;

import com.cocoon.cop.domain.main.Category;

public interface AccountRepositoryCustom {

    Long findMemberIdByAccountId(Long accountId);
    int expense(Long accountId, int amount);
}
