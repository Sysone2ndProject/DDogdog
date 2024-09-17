package com.sysone.ddogdog.common.config;


import com.sysone.ddogdog.common.config.form.CustomDetailService;
import com.sysone.ddogdog.common.config.form.CustomLoginFailureHandler;
import com.sysone.ddogdog.common.config.form.CustomLoginSuccessHandler;
import com.sysone.ddogdog.common.config.oauth.Oauth2LoginSuccessHandler;
import com.sysone.ddogdog.common.config.oauth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomDetailService customDetailService;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    //TODO : CustomAccessDeniedHandler 코드가 요청을 낚아채가는것을 확인해 일시적으로 주석 처리 추후재작성 필요
//    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private final PrincipalOauth2UserService principalOauth2UserService;
    private final Oauth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .sessionFixation().changeSessionId()
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(true)
                    .expiredUrl("/v1/owners")
            );

        http
            .authorizeHttpRequests(auth ->
                auth.requestMatchers("/v1/owners/signup", "/v1/owners", "/resource/**",
                        "/v1/owners/login","/", "/login**", "/css/**", "/js/**","/v1/customers/signup").permitAll()
                    .requestMatchers("/v1/owners/user").hasRole("OWNER")
//                        .anyRequest().permitAll())
                    // TODO : hasRole 로 권한 체크 및 실패 핸들러 작성 필요
                        .requestMatchers("/v1/customers/myPage").hasRole("CUSTOMER").anyRequest().permitAll())
            .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(
                    customAuthenticationEntryPoint));
//                .accessDeniedHandler(customAccessDeniedHandler))
//            .csrf(Customizer.withDefaults());

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
            )
            .oauth2Login(oauth2Login -> oauth2Login
                    .loginPage("/v1/customers/loginpage") // 커스텀 로그인 페이지 설정
//                    .defaultSuccessUrl("/v1/customers/mainpage") // 로그인 성공 시 이동할 페이지
                    .userInfoEndpoint(userInfoEndpoint ->
                        userInfoEndpoint
                            .userService(principalOauth2UserService) // 사용자 정보 처리
                    )
                    .successHandler(oAuth2LoginSuccessHandler)
            )
            .logout(logout -> logout
                .logoutUrl("/v1/owners/logout") // 로그아웃 요청 URL
                .logoutSuccessUrl("/v1/owners") // 로그아웃 성공 후 이동할 URL
                .invalidateHttpSession(true) // 세션 무효화
                .deleteCookies("JSESSIONID") // 쿠키 삭제
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

    @Bean
    public DefaultOAuth2UserService oAuth2UserService() {
        return new DefaultOAuth2UserService();
    }
}