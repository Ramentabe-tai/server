package com.cocoon.cop.controller;

import com.cocoon.cop.dto.LoginDto;
import com.cocoon.cop.dto.MemberDto;
import com.cocoon.cop.service.MemberService;
import com.cocoon.cop.service.security.CustomMemberDetails;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/member")
@Tag(name = "Member", description = "Member API")
public class LoginController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@ModelAttribute LoginDto loginRequest, HttpServletRequest request) {

        log.info("loginRequest = {}", loginRequest);

        try {
            // 필터에서 생성한 토큰 가져오기
            String token = (String) request.getAttribute("jwtToken");
            if (token == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token generation failed");
            }

            // 요청 속성에서 사용자 정보 가져오기
            CustomMemberDetails userDetails = (CustomMemberDetails) request.getAttribute("userDetails");
            if (userDetails == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User details not found");
            }
            log.info("token = {}", token);
            log.info("userDetails = {}", userDetails);

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("role", userDetails.getAuthorities());
            log.info("userDetails.getId() = {}", userDetails.getId());

            MemberDto byIdToDto = memberService.findByIdToDto(userDetails.getId());
            response.put("member", byIdToDto);



            return ResponseEntity.ok().body(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
        }
    }

    @PostMapping("/login/v2")
    public ResponseEntity<?> loginV2(@ModelAttribute LoginDto loginRequest) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(Authentication authentication) {
        log.info("authentication = {}", authentication);
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
        }

        CustomMemberDetails userDetails = (CustomMemberDetails) authentication.getPrincipal();
        // 사용자 정보를 더 구체적으로 포함하여 반환

        Map<String, Object> profile = new HashMap<>();

        profile.put("email", userDetails.getUsername());
        profile.put("role", userDetails.getAuthorities().toString());

        return ResponseEntity.ok(profile);
    }
}
