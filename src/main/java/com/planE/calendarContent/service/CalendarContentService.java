package com.planE.calendarContent.service;

import com.planE.calendarContent.dto.CalendarContentDto;
import com.planE.calendarContent.repository.CalendarContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarContentService {

    @Autowired
    CalendarContentRepository calendarContentRepository;

    // 캘린더 전체 조회
    public List<CalendarContentDto> findAll(CalendarContentDto dto) {
        return calendarContentRepository.findAll(dto);
    }

    // 캘린더 전체 조회
    public CalendarContentDto findOne(CalendarContentDto dto) {
        return calendarContentRepository.findOne(dto);
    }

    public void add(CalendarContentDto dto) {
        dto.setSysSvcId("CalendarContentService");
        calendarContentRepository.add(dto);
    }

    public void delete(CalendarContentDto dto) {
        dto.setSysSvcId("CalendarContentService");
        calendarContentRepository.delete(dto);
    }

    public void update(CalendarContentDto dto) {
        dto.setSysSvcId("CalendarContentService");
        calendarContentRepository.update(dto);
    }
}
