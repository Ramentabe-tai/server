package com.cocoon.cop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userService;

    public SecurityConfig(@Lazy UserDetailsService userService) {
        this.userService = userService;
    }

    // 스프링 시큐리티 기능 비활성화
    @Bean
    public WebSecurityCustomizer configure() { // 스프링 시큐리티의 모든 기능 (인증, 인가) 를 사용하지 않게 설정
        return (web) -> web.ignoring()  // requestMatchers() 에 적힌 url 에 대해 인증, 인가 서비스를 적용하지 않음
                .requestMatchers("/static/**"); // 특정 요청과 일치하는  url 에 대한 액세스 설정
    }

    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // 특정 HTTP 요청에 대해 웹 기반 보안 구성. 인증/인가 및 로그인, 로그아웃 설정
        return http
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/**").permitAll() // 누구나 접근 가능. requestMatchers() 에 기재된 url 은 인증, 인가 없어도 접근이 가능함
                        .anyRequest().authenticated() // anyRequest() : 해당 코드 윗줄에서 설정한 url 이외의 요청에 대해 설정, authenticated() : 인가는 필요하지 않지만 인증이 필요함
                )
                .formLogin(form -> form
                        .loginPage("/login") // 로그인 페이지 설정
                        .defaultSuccessUrl("/") // 로그인 성공시 이동할 결로
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/") // 로그아웃 성공시 이동할 경로
                        .invalidateHttpSession(true) // 로그아웃 이후에 세션 전체 삭제 여부
                )
                .csrf(csrf -> csrf // CSRF 설정 비활성화, 원래는 CSRF 공격을 방지하기 위해 활성화하는게 좋음
                        .disable()
                )
                .cors(withDefaults())
//                .oauth2Login(Customizer.withDefaults())
                .build();
    }

    // 인증 관리자 관련 설정, 사용자 정보를 가져올 서비스를 재정의 하거나, 인증 방법 등을 설정
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(userService);  // 사용자 정보를 가져올 서비스를 설정. 이때 설정하는 클래스는 반드시 UserDetailsService 를 상속받은 클래스이여야 함.
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder()); // 비밀번호 암호화를 위한 인코더 설정

        return daoAuthenticationProvider;
    }

    // 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화를 위한 빈 등록
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // 실제 사용 시, '*' 대신 구체적인 오리진 지정
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}