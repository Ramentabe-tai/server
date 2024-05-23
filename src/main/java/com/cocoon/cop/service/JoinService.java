package com.cocoon.cop.service;

import com.cocoon.cop.domain.main.Member;
import com.cocoon.cop.dto.JoinDto;
import com.cocoon.cop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JoinService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public void join(JoinDto joinDto) {

        String encodedPass = passwordEncoder.encode(joinDto.getPassword());
        joinDto.setPassword(encodedPass);

        if(memberRepository.existsByEmail(joinDto.getEmail())) {
            throw new IllegalArgumentException("既に登録されているメールアドレスです。");
        }

        Member member = memberRepository.save(joinDto.toEntity());
        log.info("会員登録が完了しました　= {}", member);
    }
}
