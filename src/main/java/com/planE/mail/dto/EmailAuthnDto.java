package com.planE.mail.dto;

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
public class EmailAuthnDto{
	private String emailAuthnId;   /** 이메일인증아이디*/
	private String email;          /** 이메일*/
	private String emailAuthnNum; /** 이메일인증번호*/
	private String emailAuthnYn;   /** 이메일인증여부*/
}
