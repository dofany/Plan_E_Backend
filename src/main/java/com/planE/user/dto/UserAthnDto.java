package com.planE.user.dto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAthnDto {
	

    private String email;       // 사용자 이메일
    private String emailAuthnNum;   // 이메일 인증번호
    private String userPw;          // 사용자 패스워드
    private String userNm;          // 사용자명
    private String resCd;           // 결과코드
    private Boolean result;         // 결과
    private String resultMsg;       // 결과 메세지
    private String userId;          // 유저 아이디


    private String loginAthnType;   // 로그인 인증 유형

    @NonNull
    private String sucesYn;         // 성공 여부

}
