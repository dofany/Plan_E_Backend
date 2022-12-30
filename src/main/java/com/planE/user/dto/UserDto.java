package com.planE.user.dto;

import com.planE.common.base.dto.BaseDto;
import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {
    private String userId; // 유저 아이디
    private String userPw; // 유저 패스워드
    private String email; // 사용자 이메일
    private String userNm; // 사용자명
    private String nickName; // 닉네임
    private String userBirthDate; // 사용자 생년월일
    private String phoneNum; // 사용자 핸드폰 번호
    private Integer lgnFailCnt; // 로그인 실패 횟수
    private Integer userStatus; // 상태
    private String userProfile; // 프로필
    private String profileSrc; // 프로필 사진 경로
}