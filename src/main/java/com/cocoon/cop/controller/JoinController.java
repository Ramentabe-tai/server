package com.cocoon.cop.controller;

import com.cocoon.cop.domain.main.Member;
import com.cocoon.cop.dto.JoinDto;
import com.cocoon.cop.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private static final Logger logger = LoggerFactory.getLogger(JoinController.class);

    private final JoinService joinService;

    /**
     * TODO: JoinDto validation 추가해야 함
     */
    @PostMapping("/member/join")
    public ResponseEntity<?> join(@RequestBody JoinDto joinDto) {

        logger.info("JoinDto = {}", joinDto);

        try {
            Member joinedMember = joinService.join(joinDto);

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("id", joinedMember.getId());
            response.put("name", joinedMember.getName());
            response.put("ruby", joinedMember.getRuby());
            response.put("email", joinedMember.getEmail());
            response.put("phoneNumber", joinedMember.getPhoneNumber());


//            SavingAccount memberSavingAccount = new SavingAccount().builder()
//                    .savingAccountNumber("78834951") //　発表時、会員登録部分はパスするので、貯金用口座番号は臨時で設定しておいた
//                    .member(joinedMember)
//                    .balance(0)
//                    .createdDate(LocalDateTime.now())
//                    .build();
//
//            savingAccountRepository.save(memberSavingAccount);
//            logger.info("memberSavingAccount = {}", memberSavingAccount);
//
//            response.put("savingAccountId", memberSavingAccount.getId());


            return ResponseEntity.ok().body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", e.getMessage()));
        }
    }
}
