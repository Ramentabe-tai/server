package com.cocoon.cop.controller;

import com.cocoon.cop.domain.main.Member;
import com.cocoon.cop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogController.class);
    private final MemberRepository memberRepository;

    @GetMapping("/memberLogTest")
    public Member memberLogTest() {

        Optional<Member> findMember = memberRepository.findMemberByEmail("whddnjs3340@naver.com");
        LOGGER.info("findMember = " + findMember);

        return findMember.orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }
}
