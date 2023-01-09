package com.planE.auth.filter;

public class SessionConst {
    // 세션 생성시, 조회시, 삭제시에 세션 객체 안에 담긴 유저 설정
    // 우선적으로는 이메일만 넣어줌 => 추후 다른 정보가 필요할시 추가하면 됨
    public static final String SESSION_USER_EMAIL = "sessionInfoEmail";
}
