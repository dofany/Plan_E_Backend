package com.planE.auth.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import static org.springframework.util.ObjectUtils.isEmpty;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class SessionFilter implements Filter {

    // 인증 필터를 적용
    // 인증과 무관하게 항상 허용
    private static final String[] exceptionList = {"/api/userAthn/login", "/api/auth/sessionLogin", "/api/auth/sessionLogout", "/api/userAthn/signUp"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("=========== 인증 체크 필터 시작 : {} ===========", requestURI);

            if (isSessionCheckPath(requestURI)) {
                log.info("=========== 인증 체크 로직 실행 : {} ===========", requestURI);
                // session이 있으면 기존 session값 할당, 없으면 null
                HttpSession session = httpRequest.getSession(false);
                if (isEmpty(session) || isEmpty(session.getAttribute(SessionConst.SESSION_USER_EMAIL))) {
                    log.info("=========== 미인증 사용자 요청 : {} ===========", requestURI);
                    // 403 : 권한이 없음 에러 / 화면에서 세션 만료 처리를 위해서 status값 set
                    httpResponse.setStatus(403);
                    // 미인증 사용자는 다음으로 진행하지 않고 끝
                    return;
                }
            }

            chain.doFilter(request, response);

        } catch (Exception e) {
            throw e;
        } finally {
            log.info("=========== 인증 체크 필터 종료 : {} ===========", requestURI);
        }

    }


    private boolean isSessionCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(exceptionList, requestURI);
    }

}
