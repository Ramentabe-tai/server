package com.cocoon.cop.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

/**
 * LogoutControllerでカスタムログアウト処理を行うため、CustomLogoutSuccessHandlerは不要
 * SpringSecurityのログアウトの基本設定を行う場合は、CustomLogoutSuccessHandlerを使用
 */
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Logout Success\"}");
        response.getWriter().flush();
    }

}
