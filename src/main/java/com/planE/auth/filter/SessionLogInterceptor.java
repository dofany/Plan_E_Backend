package com.planE.auth.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class SessionLogInterceptor implements HandlerInterceptor {

    public static final String LOG_ID = "logId";

    // 컨트롤러 호출 전에 호출
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse
            response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        //인터셉터는 싱글톤패턴이기 때문에 setAttribute를 활용하여 밑에 있는 afterCompletion에게 UUID값을 전달한다
        request.setAttribute(LOG_ID, uuid);

        if (handler instanceof HandlerMethod) {
            //호출할 컨트롤러 메서드의 모든 정보가 포함되어 있다.
            HandlerMethod hm = (HandlerMethod) handler;
        }

        log.info("============================= REQUEST  [{}] / [{}] / [{}] =============================", uuid, requestURI, handler);
        return true; //false면 다음 호출이 진행되지 않는다

    }

    // 컨트롤러 호출 후에 호출
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse
            response, Object handler, ModelAndView modelAndView) throws Exception {

        log.info("============================= postHandle : [{}] =============================", modelAndView);

    }

    // 뷰 랜더링 후에 호출
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse
            response, Object handler, Exception ex) throws Exception {

        String requestURI = request.getRequestURI();
        String logId = (String)request.getAttribute(LOG_ID);
        log.info("============================= RESPONSE : [{}] / [{}] =============================", logId, requestURI);
        if (ex != null) {
            log.error("============================= afterCompletion error!! =============================", ex);
        }
    }
}
