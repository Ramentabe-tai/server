package com.cocoon.cop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class PaymentTransactionDto {

    private String month;
    private int amount;

    // 필드 이름을 동적으로 변경하기 위한 메서드 추가
    public String getFieldName(String type) {
        return type.equals("DEPOSIT") ? "deposit" : "spending";
    }

}
