package com.cocoon.cop.dto;

import com.cocoon.cop.domain.enums.Role;
import com.cocoon.cop.domain.main.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 会員登録テスト用DTO
 */
@Setter @Getter
@ToString
public class JoinDto {

    private String name;
    private String ruby;
    private String email;
    private String password;
    private String confirmPassword;
    private String phoneNumber;


    public Member toEntity() {
        return Member.builder()
                .name(name)
                .ruby(ruby)
                .email(email)
                .password(password)
                .role(Role.fromKey("ROLE_USER").getKey())
                .phoneNumber(phoneNumber)
                .build();
    }
}
