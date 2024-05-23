package com.cocoon.cop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberSignUpRequest {

    @NotNull
    private String name;

    @NotNull
    private String email;

    private String password;
    private String phone;
}
