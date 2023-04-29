package com.planE.common.config.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class SessionLogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("============================= 세션 로직 필터 생성 =============================");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain
    ) throws IOException, ServletException {
        log.info("============================= 필터 체인으로 연결 =============================");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        String uuid = UUID.randomUUID().toString(); // HTTP 요청을 구분하기 위해 요청당 임의의 uuid 생성

        try {
            log.info("============================= REQUEST : [{}] / [{}] =============================", uuid, requestURI);
            chain.doFilter(request, response);
        } catch (Exception e) {
            log.error("============================= 인증 체크 필터 에러 : {} =============================", e.getMessage());
            throw new RuntimeException(e);
        } finally {
            log.info("============================= RESPONSE : [{}] / [{}] =============================", uuid, requestURI);
        }

    }

//    public static final String LOG_ID = "logId";
//
//    // 컨트롤러 호출 전에 호출
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse
//            response, Object handler) throws Exception {
//
//        String requestURI = request.getRequestURI();
//        String uuid = UUID.randomUUID().toString();
//
//        //인터셉터는 싱글톤패턴이기 때문에 setAttribute를 활용하여 밑에 있는 afterCompletion에게 UUID값을 전달한다
//        request.setAttribute(LOG_ID, uuid);
//
//        if (handler instanceof HandlerMethod) {
//            //호출할 컨트롤러 메서드의 모든 정보가 포함되어 있다.
//            HandlerMethod hm = (HandlerMethod) handler;
//            log.info("============================= Handler Method : {} =============================" , hm);
//        }
//
//        log.info("============================= REQUEST : [{}] / [{}] / [{}] =============================", uuid, requestURI, handler);
//        return true; //false면 다음 호출이 진행되지 않는다
//
//    }
//
//    // 컨트롤러 호출 후에 호출
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse
//            response, Object handler, ModelAndView modelAndView) throws Exception {
//
//        log.info("============================= postHandle : [{}] =============================", modelAndView);
//
//    }
//
//    // 뷰 랜더링 후에 호출
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse
//            response, Object handler, Exception ex) throws Exception {
//
//        String requestURI = request.getRequestURI();
//        String logId = (String)request.getAttribute(LOG_ID);
//        log.info("============================= RESPONSE : [{}] / [{}] =============================", logId, requestURI);
//        if (ex != null) {
//            log.error("============================= AfterCompletion : {} =============================", ex);
//        }
//    }
}
