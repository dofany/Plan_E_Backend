package com.planE.calendarContent.repository;

import com.planE.calendarContent.dto.CalendarContentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalendarContentRepository {

    List<CalendarContentDto> findAll(CalendarContentDto dto);

    CalendarContentDto findOne(CalendarContentDto dto);

    void add(CalendarContentDto dto);

    void delete(CalendarContentDto dto);

    void update(CalendarContentDto dto);
}
