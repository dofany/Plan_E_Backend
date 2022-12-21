package com.planE.user.dto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginInputDto {
	
	
    private String userEmail;
    private String userPw;
    private String userName;
    @NonNull
    private String inputEmail;
    @NonNull
    private String inputPw;
}
