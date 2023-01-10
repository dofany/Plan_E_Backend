package com.planE.common.session.controller;

import static org.springframework.util.ObjectUtils.isEmpty;

import com.planE.common.config.interceptor.SessionConst;
import com.planE.user.dto.UserDto;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api("세션 로그인 및 로그아웃")
@RestController
@RequestMapping("${property.api.end-point}")
@Slf4j
@CrossOrigin("*")
public class SessionController {

    // 로그인, 세션로그인
    @PostMapping("/auth/sessionLogin")
    public String login(@RequestBody UserDto user, HttpServletRequest request){
        // userAuthService에서 유저 조회시 유저가 없을땐 N / 있으면 세션값 생성 및 email 정보 세션안으로 set
        if (isEmpty(user)) {
            return "N";
        } else {
            HttpSession session = request.getSession(true);
            session.setAttribute(SessionConst.SESSION_USER_EMAIL, user.getEmail());
            return "Y";
        }
    }

    // 로그아웃, 세션 로그아웃
    @GetMapping("/auth/sessionLogout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();

        log.info("=========== session : {} ===========", session);
        log.info("=========== session before state : {} ===========", session.getAttribute(SessionConst.SESSION_USER_EMAIL));

        if(session == null) {
            return "N";
        } else {
            // 세션 삭제
            session.removeAttribute(SessionConst.SESSION_USER_EMAIL);
            // 사용자가 요청을 또 보내면 모든 정보가 그대로 남아있기 때문에 재생성
            // request.getSession(true);
            log.info("=========== session delete state : {} ===========", request.getSession());
            log.info("=========== session after state : {} ===========", session.getAttribute(SessionConst.SESSION_USER_EMAIL));
            return "Y";
        }
    }
}
