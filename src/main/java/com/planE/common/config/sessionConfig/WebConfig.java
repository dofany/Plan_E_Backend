package com.planE.common.config.sessionConfig;

import com.planE.common.config.filter.SessionFilter;
import com.planE.common.config.filter.SessionLogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class WebConfig /*implements WebMvcConfigurer*/ {

    // Filter 순서 지정 및 Bean 객체 등록
    @Bean
    public FilterRegistrationBean sessionLogFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new SessionLogFilter());
        filterFilterRegistrationBean.setOrder(1);
        // URL 전체 영향
        filterFilterRegistrationBean.addUrlPatterns("/*");
        return filterFilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean sessionFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new SessionFilter());
        filterFilterRegistrationBean.setOrder(2);
        filterFilterRegistrationBean.addUrlPatterns("/*");
        return filterFilterRegistrationBean;
    }


//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        //로그를 출력하는 인터셉터
//        registry.addInterceptor(new SessionLogFilter())
//                .order(1)
//                .addPathPatterns("/**")
//                .excludePathPatterns(
//                        "/api/userAthn/login",
//                        "/api/auth/sessionLogin",
//                        "/api/auth/sessionLogout",
//                        "/api/userAthn/signUp",
//                        "/api/userAthn/emailCheck",
//                        "/api/userAthn/pwChg",
//                        "/error"
//                );
//
//        //로그인 체크 인터셉터
//        registry.addInterceptor(new SessionFilter())
//                //로그 인터셉터 뒤에 작동할 수 있도록 설정
//                .order(2)
//                //인터셉터를 적용할 URL 패턴 설정
//                .addPathPatterns("/**")
//                //인터셉터 적용을 제외 할 URL 패턴 설정
//                .excludePathPatterns(
//                        "/api/userAthn/login",
//                        "/api/auth/sessionLogin",
//                        "/api/auth/sessionLogout",
//                        "/api/userAthn/signUp",
//                        "/api/userAthn/emailCheck",
//                        "/api/userAthn/pwChg",
//                        "/error"
//                );
//    }

}
