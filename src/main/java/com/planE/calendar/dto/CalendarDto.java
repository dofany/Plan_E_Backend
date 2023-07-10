package com.planE.calendar.dto;

import com.planE.common.base.dto.BaseDto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarDto extends BaseDto {
    private String calendarId; // 캘린더 아이디
    private String userId; // 사용자 아이디
    private String calendarName; // 캘린더명
    private String calendarContent; // 캘린더내용
    private String baseCalendarYn; // 기본캘린더여부
}
