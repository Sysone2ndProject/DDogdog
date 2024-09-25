package com.sysone.ddogdog.common.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class MultiAuthorityBasedSessionInvalidationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestUri = request.getRequestURI();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            boolean hasOwnerAuthority = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch(auth -> auth.equals("ROLE_OWNER"));
            boolean hasAdminAuthority = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch(auth -> auth.equals("ROLE_CUSTOMER"));

            // 요청 URL과 권한에 따라 세션을 무효화하고 리다이렉트
            if (requestUri.equals("/v1/customers") && hasOwnerAuthority) {
                // ROLE_OWNER 권한으로 접근 시 세션 무효화
                request.getSession().invalidate();
                SecurityContextHolder.clearContext();
                response.sendRedirect("/v1/customers"); // ROLE_OWNER 리다이렉트
                return;
            } else if (requestUri.equals("/v1/owners") && hasAdminAuthority) {
                // ROLE_ADMIN 권한으로 접근 시 세션 무효화
                request.getSession().invalidate();
                SecurityContextHolder.clearContext();
                response.sendRedirect("/v1/owners"); // ROLE_ADMIN 리다이렉트
                return;
            }
        }

        // 다음 필터로 요청 전달
        filterChain.doFilter(request, response);
    }
}