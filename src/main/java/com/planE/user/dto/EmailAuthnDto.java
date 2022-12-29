package com.planE.user.dto;

import com.planE.common.base.dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailAuthnDto extends BaseDto {

	private String emailAuthnId;   /** 이메일인증아이디*/
	private String email;          /** 이메일*/
	private String emailAuthnNum;  /** 이메일인증번호*/
	private String emailAuthnYn;   /** 이메일인증여부*/
	private String resCd;	   /** 응답코드*/
}
