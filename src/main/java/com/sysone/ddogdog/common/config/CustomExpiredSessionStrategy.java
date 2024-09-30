package com.sysone.ddogdog.common.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

@Component
public class CustomExpiredSessionStrategy implements InvalidSessionStrategy, SessionInformationExpiredStrategy {

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uri = request.getRequestURI();

        if (uri.startsWith("/v1/owners")) {
            response.sendRedirect("/v1/owners");
        }
        if (uri.startsWith("/v1/customers")) {
            response.sendRedirect("/v1/customers");
        }
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        String uri = event.getRequest().getRequestURI();
        if (uri.startsWith("/v1/owners")) {
            event.getResponse().sendRedirect("/v1/owners");
        }
        if (uri.startsWith("/v1/customers")) {
            event.getResponse().sendRedirect("/v1/customers");
        }
    }
}