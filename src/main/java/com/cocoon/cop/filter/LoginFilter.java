package com.cocoon.cop.filter;

import com.cocoon.cop.service.security.CustomMemberDetails;
import com.cocoon.cop.utils.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Iterator;

/**
 * AuthenticationManagerをInjectionして、これで検証を行う
 * 検証する時は UsernamePasswordAuthenticationTokenというDTOに入れて送る
 * 検証が成功すると、successfulAuthentication()が呼ばれる、失敗するとunsuccessfulAuthentication()が呼ばれる
 * TODO: 成功時、失敗時の処理の実装
 */
@RequiredArgsConstructor
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

//    @Value("${spring.jwt.expiration}")
//    private Long expiration;


    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String username = request.getParameter("email");
        log.info("Attempting to log in with username: {}", username);
        return username;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // クライアントのRequestからusername, passwordを抽出
        String username = obtainUsername(request);// email
        String password = obtainPassword(request);

        log.info("username = {}", username);
        log.info("password = {}", password);

        // Spring SecurityでUsernameとpasswordを検証するためにはtokenに入れ込まないといけない
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        // tokenの検証のためのAuthenticationManagerに渡す
        return authenticationManager.authenticate(authToken);
    }

    /**
     * ログイン成功時の処理。　（ここでJWTを発行）
     * ログイン成功時　successfulAuthentication() methodを通じてJWTを発行しないといけない。
     * JWT　Responseを作成するけど、JWT発行クラスをまだ作ってないから、DB基盤の検証を行ってからJWTの発行,検証するクラスを生成しないといけない。
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

        CustomMemberDetails customMemberDetails = (CustomMemberDetails) authentication.getPrincipal();

        String email = customMemberDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();

        String token = jwtUtil.createJwt(email, role, 60 * 60 * 1000L * 10); // 10時間
        log.info("token = {}", token);

        response.addHeader("Authorization", "Bearer " + token);
    }

    /**
     * ログイン失敗時の処理
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(401);
    }


}
