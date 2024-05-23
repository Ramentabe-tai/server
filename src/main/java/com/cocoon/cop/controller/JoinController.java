package com.cocoon.cop.controller;

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

import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private static final Logger logger = LoggerFactory.getLogger(JoinController.class);

    private final JoinService joinService;

    @PostMapping("/member/join")
    public ResponseEntity<?> join(@ModelAttribute JoinDto joinDto) {

        logger.info("JoinDto = {}", joinDto);

        try {
            joinService.join(joinDto);
            return ResponseEntity.ok().body(Collections.singletonMap("message", "Join Success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Join Failed"));
        }
    }
}
