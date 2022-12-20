package com.planE.user.dto;

import com.planE.common.base.dto.BaseDto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginHstDto extends BaseDto {
	
	private String userId; // 유저 아이디
	private String loginAthnType; // 로그인 인증 유형
	private String sucesYn; // 성공 여부

}
