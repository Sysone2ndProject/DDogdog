package com.sysone.ddogdog.common.config;


import com.sysone.ddogdog.common.config.form.CustomDetailService;
import com.sysone.ddogdog.common.config.form.CustomLoginFailureHandler;
import com.sysone.ddogdog.common.config.form.CustomLoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomDetailService customDetailService;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation().changeSessionId()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/v1/owners")
            );

        http
            .authorizeHttpRequests(auth ->
                auth.requestMatchers("/v1/owners/signup", "/v1/owners", "/resource/**", "/v1/owners/login").permitAll()
                    .requestMatchers("/v1/owners/user").hasRole("OWNER")
                    .anyRequest().permitAll())
            .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(customAuthenticationEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler))
            .csrf(Customizer.withDefaults());

        http.userDetailsService(customDetailService);

        http
            .formLogin(form -> form
                .loginPage("/v1/owners/login")//로그인 폼위치
                .loginProcessingUrl("/loginPro")  // 커스텀 로그인 처리 경로 지정
                .usernameParameter("id")  // 사용자 이름 파라미터 설정
                .passwordParameter("password")  // 패스워드 파라미터 설정
                .successHandler(authenticationSuccessHandler())//로그인 성공 핸들러
                .failureHandler(authenticationFailureHandler())
                .permitAll()// 모든 사용자에게 로그인 페이지 접근 허용
            );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        //로그인 성공시 실행할 부분
        return new CustomLoginSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomLoginFailureHandler();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}