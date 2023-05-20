package com.planE.calendar.repository;

import com.planE.calendar.dto.CalendarDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalendarRepository {
    List<CalendarDto> findAll(CalendarDto dto); // 캘린더 전체 조회

    void add(CalendarDto dto); // 캘린더 생성

    void edit(CalendarDto dto); // 캘린더 수정

    void delete(CalendarDto dto); // 캘린더 삭제
}
