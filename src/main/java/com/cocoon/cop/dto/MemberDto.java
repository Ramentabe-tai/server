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
    private String email;
    private Role role;
    private String phoneNumber;
    private int rankPoint;
    private Long accountId;
    private Long profileImageId;
}
