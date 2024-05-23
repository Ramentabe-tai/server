package com.cocoon.cop.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class GoogleLoginController {

    private final HttpSession httpSession;

//    @GetMapping("/login/oauth2/google")
//    public String home(Model model) {
//        model.addAttribute("username", "jongwon");
//        return "hello";
//    }


}
