package com.planE.common.commonCode.dto;

import com.planE.common.base.dto.BaseDto;
import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComcdDto extends BaseDto {

    private String cdGroupId;
    private String cdGroupNm;
    private String cdGroupEngNm;
    private String cdGroupSbst;
    private String cdId;
    private String cdName;
    private String cdSbst;
    private String useYn;

}
