package com.cocoon.cop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final Logger logger = Logger.getLogger(MemberService.class.getName());
}
