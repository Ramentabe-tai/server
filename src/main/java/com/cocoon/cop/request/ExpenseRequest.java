package com.cocoon.cop.request;

public record ExpenseRequest(int amount, Long categoryId, String memo) {
}

