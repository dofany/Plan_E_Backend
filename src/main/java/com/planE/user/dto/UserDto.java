package com.planE.user.dto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userId;
    private String userPw;
    private String userName;
}