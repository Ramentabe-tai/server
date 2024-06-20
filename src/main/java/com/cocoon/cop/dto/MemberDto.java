package com.cocoon.cop.dto;

import com.cocoon.cop.domain.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class MemberDto {

    private Long id;
    private String name;
    private String ruby;
    private String email;
//    private Role role;
    private String phoneNumber;
    private int rankPoint;
    private Long accountId;
//    private Long profileImageId;


    public MemberDto(Long id, String name, String ruby, String email, String phoneNumber, int rankPoint, Long accountId, Long savingAccountId) {
        this.id = id;
        this.name = name;
        this.ruby = ruby;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.rankPoint = rankPoint;
        this.accountId = accountId;
    }
}
