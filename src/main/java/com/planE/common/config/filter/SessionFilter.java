package com.planE.common.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
public class SessionFilter implements Filter {
    // 인증 필터를 적용
    // 인증과 무관하게 항상 허용
    private static final String[] exceptionList = {
            "/api/userAthn/login",
            "/api/auth/sessionLogin",
            "/api/auth/sessionLogout",
            "/api/userAthn/signUp",
            "/api/userAthn/emailCheck",
            "/api/userAthn/pwChg",
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("============================= 인증 체크 필터 시작 : {} =============================", requestURI);

            if (isSessionCheckPath(requestURI)) {
                if("Y".equals(httpRequest.getHeader("testYn"))) {
                    chain.doFilter(request, response);
                    return;
                }
                log.info("============================= 인증 체크 로직 실행 : {} =============================", requestURI);
                // session이 있으면 기존 session값 할당, 없으면 null
                HttpSession session = httpRequest.getSession(false);
                if (isEmpty(session) || isEmpty(session.getAttribute(SessionConst.SESSION_USER_EMAIL))) {
                    log.info("============================= 미인증 사용자 요청 : {} =============================", requestURI);
                    // 403 : 권한이 없음 에러 / 화면에서 세션 만료 처리를 위해서 status값 set
                    httpResponse.setStatus(403);
                    log.info("============================= HTTP 상태코드 : {} =============================",httpResponse.getStatus());
                    // 미인증 사용자는 다음으로 진행하지 않고 끝
                    return;
                } else {
                    log.info("============================= JSESSIONID  : {} =============================", session.getId());
                    log.info("============================= 세션 정보 : {} =============================", session.getAttribute(SessionConst.SESSION_USER_EMAIL));
                    log.info("============================= 세션 생성 날짜 : {} =============================", new Date(session.getCreationTime()));
                    log.info("============================= 세션 유효 시간 : {}분 =============================", session.getMaxInactiveInterval() / 60);
                    log.info("============================= 최근 세션 접근 시간 : {} =============================", new Date(session.getLastAccessedTime()));
                }
            }

            chain.doFilter(request, response);

        } catch (Exception e) {
            throw e;
        } finally {
            log.info("============================= 인증 체크 필터 종료 : {} =============================", requestURI);
        }

    }


    private boolean isSessionCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(exceptionList, requestURI);
    }

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String requestURI = request.getRequestURI();
//        log.info("============================= 인증 체크 인터셉터 실행 : {} =============================", requestURI);
//        HttpSession session = request.getSession(false);
//
//
//        // 포스트맨 테스트 예외처리
//        if("Y".equals(request.getHeader("testYn"))) {
//            return true;
//
//        } else {
//            //인증이 불가능한 사용자의 접근 검증
//            if (isEmpty(session)  || isEmpty(session.getAttribute(SessionConst.SESSION_USER_EMAIL))) {
//                log.info("============================= 미인증 사용자 요청 =============================");
//
//                response.setStatus(403);
//
//                //컨트롤러 호출을 막기 위해 false 반환
//                return false;
//
//            } else {
//
//                log.info("============================= JSESSIONID  : {} =============================", session.getId());
//                log.info("============================= 세션 정보 : {} =============================", session.getAttribute(SessionConst.SESSION_USER_EMAIL));
//                log.info("============================= 세션 생성 날짜 : {} =============================", new Date(session.getCreationTime()));
//                log.info("============================= 세션 유효 시간 : {}분 =============================", session.getMaxInactiveInterval() / 60);
//                log.info("============================= 최근 세션 접근 시간 : {} =============================", new Date(session.getLastAccessedTime()));
//
//                //인증이 가능한 사용자는 다음 컨트롤러 호출
//                return true;
//
//            }
//        }
//    }
}
