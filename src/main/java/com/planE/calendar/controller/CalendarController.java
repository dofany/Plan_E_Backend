package com.planE.calendar.controller;


import com.planE.calendar.dto.CalendarDto;
import com.planE.calendar.service.CalendarService;
import com.planE.common.config.filter.SessionConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Api("캘린더")
@RestController
@RequestMapping("${property.api.end-point}")
@Slf4j
public class CalendarController {

    @Autowired
    CalendarService calendarService;

    // 캘린더 목록 조회 => <로그인 되어있는 사용자의>
    @ApiOperation("캘린더 목록 조회")
    @PostMapping("/calendar/findAll")
    public List<CalendarDto> findAll(@RequestBody CalendarDto dto, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        dto.setUserId((String) session.getAttribute(SessionConst.SESSION_USER_ID));
        return calendarService.findAll(dto);
    }

    // 캘린더 생성 => <로그인 되어있는 사용자의>
    @ApiOperation("캘린더 생성")
    @PostMapping("/calendar/add")
    public void add(@RequestBody CalendarDto dto, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        dto.setUserId((String) session.getAttribute(SessionConst.SESSION_USER_ID));
        calendarService.add(dto);
    }

    // 캘린더 수정
    @ApiOperation("캘린더 수정")
    @PostMapping("/calendar/edit")
    public void edit(@RequestBody CalendarDto dto) {
        calendarService.edit(dto);
    }

    // 캘린더 삭제
    @ApiOperation("캘린더 삭제")
    @PostMapping("/calendar/delete")
    public void delete(@RequestBody CalendarDto dto) {
        calendarService.delete(dto);
    }

}
