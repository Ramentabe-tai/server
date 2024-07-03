package com.cocoon.cop.service;

import com.cocoon.cop.dto.MemberDto;
import com.cocoon.cop.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class MemberService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MemberService.class);
    private final Logger logger = Logger.getLogger(MemberService.class.getName());
    private final MemberRepository memberRepository;

    public MemberDto findByIdToDto(Long memberId) {
        logger.info("MemberService.findByIdToDto() called");
        MemberDto memberDto = memberRepository.findByIdToDto(memberId);
        log.info("memberDto = {}", memberDto);
        return memberDto;
    }
}
