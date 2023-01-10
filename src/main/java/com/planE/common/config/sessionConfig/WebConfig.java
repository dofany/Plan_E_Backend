package com.planE.common.config.sessionConfig;

import com.planE.common.config.interceptor.SessionInterceptor;
import com.planE.common.config.interceptor.SessionLogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //로그를 출력하는 인터셉터
        registry.addInterceptor(new SessionLogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/api/userAthn/login",
                        "/api/auth/sessionLogin",
                        "/api/auth/sessionLogout",
                        "/api/userAthn/signUp",
                        "/api/userAthn/emailCheck",
                        "/api/userAthn/pwChg",
                        "/error"
                );

        //로그인 체크 인터셉터
        registry.addInterceptor(new SessionInterceptor())
                //로그 인터셉터 뒤에 작동할 수 있도록 설정
                .order(2)
                //인터셉터를 적용할 URL 패턴 설정
                .addPathPatterns("/**")
                //인터셉터 적용을 제외 할 URL 패턴 설정
                .excludePathPatterns(
                        "/api/userAthn/login",
                        "/api/auth/sessionLogin",
                        "/api/auth/sessionLogout",
                        "/api/userAthn/signUp",
                        "/api/userAthn/emailCheck",
                        "/api/userAthn/pwChg",
                        "/error"
                );
    }

}
