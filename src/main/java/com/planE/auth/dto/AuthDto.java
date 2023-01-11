package com.planE.auth.dto;

import com.planE.common.base.dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto extends BaseDto {

	private String authId; /** 권한아이디*/
	private String authNm; /** 권한이름*/
	private String useYn; /** 사용여부*/
	
    private String sucesYn; /** 성공여부*/
}
