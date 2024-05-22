package com.cocoon.cop.domain.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("ROLE_ADMIN", "管理者"),
    USER("ROLE_USER", "会員");

    private final String key;
    private final String name;
}
