package com.planE.calendarContent.dto;

import com.planE.common.base.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalendarContentDto extends BaseDto {
    private String calendarContentId;
    private String calendarId;
    private String userId;
    private String title;
    private String detailContent;
    private String startDt;
    private String endDt;
    private String startTime;
    private String endTime;
    private String alarmYn;
    private String alarmTime;
}
