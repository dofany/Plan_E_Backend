package com.planE.menuAuth.dto;

import com.planE.common.base.dto.BaseDto;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuAuthDto extends BaseDto {

    private String menuAuthId;    // 메뉴권한아이디
    private String authId;         // 권한아이디
    private String menuId;         // 메뉴아이디
    private String menuNm;         // 메뉴명
    private String useYn;          // 사용여부
}
