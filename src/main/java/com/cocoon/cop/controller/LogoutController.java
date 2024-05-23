package com.cocoon.cop.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class LogoutController {

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @PostMapping("/member/customLogout")
    public ResponseEntity<?> logout(HttpServletRequest request) {

        try {
            request.getSession().invalidate(); // .invalidateHttpSession(true)と同じ
            SecurityContextHolder.clearContext(); // SpringSecurity ContextをClear

            // Logout成功時のResponse
            return ResponseEntity.ok().body(Collections.singletonMap("message", "Logout Success"));
        } catch (Exception e) {

            // Logout失敗時のResponse
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Logout Failed"));
        }

    }


}
