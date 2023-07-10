package com.planE.calendarContent.controller;

import com.planE.calendarContent.dto.CalendarContentDto;
import com.planE.calendarContent.service.CalendarContentService;
import com.planE.common.config.filter.SessionConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Api("캘린더")
@RestController
@RequestMapping("${property.api.end-point}")
@Slf4j
public class CalendarContentController {

    @Autowired
    private CalendarContentService calendarContentService;

    @ApiOperation("캘린더 일정 목록 조회")
    @PostMapping("/calendar/content/findAll")
    public List<CalendarContentDto> findAll(@RequestBody CalendarContentDto dto, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        dto.setUserId((String) session.getAttribute(SessionConst.SESSION_USER_ID));
        if(dto.getCalendarId() == null || dto.getCalendarId().equals("")) {
            dto.setCalendarId((String) session.getAttribute(SessionConst.SESSION_BASE_CALENDAR_ID));
        }
        return calendarContentService.findAll(dto);
    }

    @ApiOperation("캘린더 일정 단건 조회")
    @PostMapping("/calendar/content/findOne")
    public CalendarContentDto findOne(@RequestBody CalendarContentDto dto, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        dto.setUserId((String) session.getAttribute(SessionConst.SESSION_USER_ID));
        if(dto.getCalendarId() == null || dto.getCalendarId().equals("")) {
            dto.setCalendarId((String) session.getAttribute(SessionConst.SESSION_BASE_CALENDAR_ID));
        }
        return calendarContentService.findOne(dto);
    }

    @ApiOperation("캘린더 일정 생성")
    @PostMapping("/calendar/content/add")
    public void add(@RequestBody CalendarContentDto dto, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        dto.setUserId((String) session.getAttribute(SessionConst.SESSION_USER_ID));
        if(dto.getCalendarId() == null || dto.getCalendarId().equals("")) {
            dto.setCalendarId((String) session.getAttribute(SessionConst.SESSION_BASE_CALENDAR_ID));
        }
        dto.setSysCreatId((String) session.getAttribute(SessionConst.SESSION_USER_ID));
        calendarContentService.add(dto);
    }

    @ApiOperation("캘린더 일정 삭제")
    @PostMapping("/calendar/content/delete")
    public void delete(@RequestBody CalendarContentDto dto, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        dto.setUserId((String) session.getAttribute(SessionConst.SESSION_USER_ID));
        if(dto.getCalendarId() == null || dto.getCalendarId().equals("")) {
            dto.setCalendarId((String) session.getAttribute(SessionConst.SESSION_BASE_CALENDAR_ID));
        }
        calendarContentService.delete(dto);
    }

    @ApiOperation("캘린더 일정 수정")
    @PostMapping("/calendar/content/update")
    public void update(@RequestBody CalendarContentDto dto, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        dto.setUserId((String) session.getAttribute(SessionConst.SESSION_USER_ID));
        if(dto.getCalendarId() == null || dto.getCalendarId().equals("")) {
            dto.setCalendarId((String) session.getAttribute(SessionConst.SESSION_BASE_CALENDAR_ID));
        }
        dto.setSysAmdrId((String) session.getAttribute(SessionConst.SESSION_USER_ID));
        calendarContentService.update(dto);
    }
}
