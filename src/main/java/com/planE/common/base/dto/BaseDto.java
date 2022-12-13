package com.planE.common.base.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto {
    private String sysCreatId; // 시스템 생성자 아이디
    private String sysAmdrId; // 시스템 수정자 아이디
    private String sysSvcId; // 시스템 서비스 아이디
    private LocalDateTime sysRecdCretDt; // 시스템 레코드 생성 일시
    private LocalDateTime sysRecdChgDt; // 시스템 레코드 변경 일시
}
