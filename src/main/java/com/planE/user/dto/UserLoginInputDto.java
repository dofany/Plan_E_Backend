package com.planE.user.dto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginInputDto {
	
	@NonNull
    private String userEmail;
    @NonNull
    private String userPw;
    private String userName;
    private String inputEmail;
    private String inputPw;
}
