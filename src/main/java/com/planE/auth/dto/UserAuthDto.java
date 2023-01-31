package com.planE.auth.dto;

import com.planE.common.base.dto.BaseDto;
import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthDto extends BaseDto {
    private String userAuthId; // 사용자권한아이디
    private String authId;     // 권한아이디
    private String userId;     // 사용자아이디
}