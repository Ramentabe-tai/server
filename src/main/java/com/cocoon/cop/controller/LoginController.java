package com.cocoon.cop.controller;

import com.cocoon.cop.dto.LoginDto;
import com.cocoon.cop.service.security.CustomMemberDetails;
import com.cocoon.cop.utils.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@ModelAttribute LoginDto loginRequest, HttpServletRequest request) {

        log.debug("loginRequest = {}", loginRequest);

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
            response.put("email", userDetails.getUsername());
            response.put("role", userDetails.getAuthorities());


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
