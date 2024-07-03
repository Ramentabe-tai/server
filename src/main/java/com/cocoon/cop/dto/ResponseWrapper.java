package com.cocoon.cop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseWrapper{
    private Map<String, Object> data = new HashMap<>();
}
