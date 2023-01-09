package com.planE.auth.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class SessionLogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("===================== 세션 로직 필터 생성 =====================");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain
    ) throws IOException, ServletException {
        log.info("===================== 필터 체인으로 연결 =====================");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        String uuid = UUID.randomUUID().toString(); // HTTP 요청을 구분하기 위해 요청당 임의의 uuid 생성

        try {
            log.info("=========== REQUEST : [{}] / [{}] ===========", uuid, requestURI);
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            log.info("=========== RESPONSE : [{}] / [{}] ===========", uuid, requestURI);
        }

    }
}
