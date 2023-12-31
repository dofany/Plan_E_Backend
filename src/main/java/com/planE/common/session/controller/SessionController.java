package com.planE.common.session.controller;

import static org.springframework.util.ObjectUtils.isEmpty;

import com.planE.calendar.dto.CalendarDto;
import com.planE.calendar.service.CalendarService;
import com.planE.common.config.filter.SessionConst;
import com.planE.user.dto.UserDto;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Api("세션 로그인 및 로그아웃")
@RestController
@RequestMapping("${property.api.end-point}")
@Slf4j
@CrossOrigin("*")
public class SessionController {

    @Autowired
    private CalendarService calendarService;

    // 로그인, 세션로그인
    @PostMapping("/auth/sessionLogin")
    public String login(@RequestBody UserDto user, HttpServletRequest request){

        CalendarDto calendarDto = new CalendarDto();
        calendarDto.setUserId(user.getUserId());
        calendarDto.setCalendarName("기본 캘린더");

        List<CalendarDto> list = calendarService.findAll(calendarDto);

        // userAuthService에서 유저 조회시 유저가 없을땐 N / 있으면 세션값 생성 및 email 정보 세션안으로 set
        if (isEmpty(user)) {
            return "N";
        } else {
            HttpSession session = request.getSession(true);
            log.info("============================= 세션 생성 전 EMAIL: {} =============================", session.getAttribute(SessionConst.SESSION_USER_EMAIL));
            log.info("============================= 세션 생성 전 USER_ID: {} =============================", session.getAttribute(SessionConst.SESSION_USER_ID));
            log.info("============================= 세션 생성 전 CALENDAR_ID: {} =============================", session.getAttribute(SessionConst.SESSION_BASE_CALENDAR_ID));
            session.setAttribute(SessionConst.SESSION_USER_EMAIL, user.getEmail());
            session.setAttribute(SessionConst.SESSION_USER_ID, user.getUserId());
            session.setAttribute(SessionConst.SESSION_BASE_CALENDAR_ID, list.get(0).getCalendarId());
            log.info("============================= JSESSIONID 생성 : {} =============================", session.getId());
            log.info("============================= 세션 생성 후 EMAIL: {} =============================", session.getAttribute(SessionConst.SESSION_USER_EMAIL));
            log.info("============================= 세션 생성 후 USER_ID: {} =============================", session.getAttribute(SessionConst.SESSION_USER_ID));
            log.info("============================= 세션 생성 후 CALENDAR_ID: {} =============================", session.getAttribute(SessionConst.SESSION_BASE_CALENDAR_ID));
            log.info("============================= 세션 생성 날짜 : {} =============================", new Date(session.getCreationTime()));
            log.info("============================= 세션 유효 시간 : {}분 =============================", session.getMaxInactiveInterval() / 60);
            log.info("============================= 최근 세션 접근 시간 : {} =============================", new Date(session.getLastAccessedTime()));
            return "Y";
        }
    }

    // 로그아웃, 세션 로그아웃
    @GetMapping("/auth/sessionLogout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();

        log.info("============================= JSESSIONID : {} =============================", session.getId());
        log.info("============================= 세션 삭제 전 EMAIL: {} =============================", session.getAttribute(SessionConst.SESSION_USER_EMAIL));
        log.info("============================= 세션 생성 전 USER_ID: {} =============================", session.getAttribute(SessionConst.SESSION_USER_ID));

        if(session == null) {
            return "N";
        } else {
            // 세션 삭제
            session.removeAttribute(SessionConst.SESSION_USER_EMAIL);
            // 사용자가 요청을 또 보내면 모든 정보가 그대로 남아있기 때문에 재생성
            // request.getSession(true);
            log.info("============================= 세션 삭제 후 EMAIL: {} =============================", session.getAttribute(SessionConst.SESSION_USER_EMAIL));
            log.info("============================= 세션 생성 후 USER_ID: {} =============================", session.getAttribute(SessionConst.SESSION_USER_ID));
            log.info("============================= 세션 삭제 날짜 : {} =============================", new Date());
            return "Y";
        }
    }
}
