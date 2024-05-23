package com.cocoon.cop.config;

import com.cocoon.cop.filter.JWTFilter;
import com.cocoon.cop.filter.LoginFilter;
import com.cocoon.cop.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userService;

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;

    /*  @RequiredArgsConstructorに代替
        public SecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
            this.authenticationConfiguration = authenticationConfiguration;
        }

        public SecurityConfig(@Lazy UserDetailsService userService) {
            this.userService = userService;
        }
    */


    // AuthenticationManagerのBean登録
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // 스프링 시큐리티 기능 비활성화
    @Bean
    public WebSecurityCustomizer configure() { // 스프링 시큐리티의 모든 기능 (인증, 인가) 를 사용하지 않게 설정
        return (web) -> web.ignoring()  // requestMatchers() 에 적힌 url 에 대해 인증, 인가 서비스를 적용하지 않음
                .requestMatchers("/static/**"); // 특정 요청과 일치하는  url 에 대한 액세스 설정
    }

    /**
     * 特定HTTP RequestについてのWEB基盤セキュリティ構成
     * 認証・認可、ログイン、ログアウト設定
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/member/join","/login", "/", "/logout").permitAll() // 誰でもアクセス可能。requestMatchers() に記載されたURLは認証、認可がなくてもアクセス可能
                        .requestMatchers("/admin").hasRole("ADMIN") // ADMIN　権限を持つユーザーだけアクセス可能
                        .anyRequest().authenticated() // anyRequest() : 該当するコードの上の行で設定したURL以外のリクエストに対して設定, authenticated() : 認可は必要ないが認証が必要
                )
                .formLogin(form -> form // ログインページををクライアント側で管理する場合は、設定不要
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .loginProcessingUrl("/login")
                                .permitAll()// 로그인 처리 URL, 이 경로로 POST 요청이 오면 스프링 시큐리티가 로그인 처리를 시도
//                        .loginPage("/login") // ログインページ設定。　設定しない場合、デフォルトのログインページが表示される
//                        .defaultSuccessUrl("/") // ログイン成功時のリダイレクト先
//                        .permitAll()
//                                .disable()
                )
                .httpBasic(auth -> auth.disable()) // HTTP基本認証はパスワードを暗号化せずに送信するため、セキュリティ上のリスクがあるため、使用しない -> JWT使用
//                .logout(logout -> logout // LogoutControllerでカスタムログアウト処理を行う場合は、設定不要
//                        .logoutUrl("/member/logout") // ログアウトRequestURL
//                        .logoutSuccessUrl("/") // ログアウト成功時のリダイレクト先。　
//                        .invalidateHttpSession(true) // ログアウト時にセッションを無効化するかどうか
//                        .permitAll()
//                )
                // JWTFilter登録
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class)
                // Filter追加、LoginFilter()引数をもらう
                // (AuthenticationManager() methodに　authenticationConfiguration()オブジェクトを追加しないといけない) -> 登録必要
                // SecurityConfig : AuthenticationManagerのBean登録していないため、AuthenticationManagerを取得できない -> 追加
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf // CSRF 설정 비활성화, 원래는 CSRF 공격을 방지하기 위해 활성화하는게 좋음
                        .disable()
                )
                .cors(withDefaults())
//                .oauth2Login(Customizer.withDefaults())
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 생성 정책 설정. STATELESS : 세션을 사용하지 않음
                )
                .build();
    }

//    // 인증 관리자 관련 설정, 사용자 정보를 가져올 서비스를 재정의 하거나, 인증 방법 등을 설정
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//
//        daoAuthenticationProvider.setUserDetailsService(userService);  // 사용자 정보를 가져올 서비스를 설정. 이때 설정하는 클래스는 반드시 UserDetailsService 를 상속받은 클래스이여야 함.
//        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder()); // 비밀번호 암호화를 위한 인코더 설정
//
//        return daoAuthenticationProvider;
//    }

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