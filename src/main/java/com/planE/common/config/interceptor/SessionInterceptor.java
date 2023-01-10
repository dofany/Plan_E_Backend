package com.planE.common.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("============================= 인증 체크 인터셉터 실행 : {} =============================", requestURI);
        HttpSession session = request.getSession(false);



        // 포스트맨 테스트 예외처리
        if("postman-test".equals(request.getHeader("Postman"))) {
            return true;

        } else {
            //인증이 불가능한 사용자의 접근 검증
            if (session == null || session.getAttribute(SessionConst.SESSION_USER_EMAIL) == null) {
                log.info("============================= 미인증 사용자 요청 =============================");
                response.setStatus(403);

                //컨트롤러 호출을 막기 위해 false 반환
                return false;

            }
        }

        //인증이 가능한 사용자는 다음 컨트롤러 호출
        return true;
    }
}
