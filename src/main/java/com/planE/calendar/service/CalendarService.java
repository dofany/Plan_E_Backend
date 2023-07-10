package com.planE.calendar.service;

import com.planE.calendar.dto.CalendarDto;
import com.planE.calendar.repository.CalendarRepository;
import com.planE.calendarContent.dto.CalendarContentDto;
import com.planE.calendarContent.repository.CalendarContentRepository;
import com.planE.common.config.filter.SessionConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CalendarService {

    @Autowired
    CalendarRepository calendarRepository;

    @Autowired
    CalendarContentRepository calendarContentRepository;

    // 캘린더 전체 조회
    public List<CalendarDto> findAll(CalendarDto dto) {
        return calendarRepository.findAll(dto);
    }

    // 캘린더 생성
    public void add(CalendarDto dto) {
        calendarRepository.add(dto);
    }

    // 캘린더 수정
    public void edit(CalendarDto dto) {
        calendarRepository.edit(dto);
    }

    // 캘린더 삭제
    public void delete(CalendarDto dto) {
        CalendarContentDto contentDto = new CalendarContentDto();
        contentDto.setCalendarId(dto.getCalendarId());
        calendarContentRepository.delete(contentDto);
        calendarRepository.delete(dto);
    }
}
