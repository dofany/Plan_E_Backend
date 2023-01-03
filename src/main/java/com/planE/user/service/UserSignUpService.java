package com.planE.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planE.user.dto.UserDto;
import com.planE.user.repository.UserSignUpRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserSignUpService {
    
    @Autowired
    UserSignUpRepository userSignUpRepository;

    @Transactional
    public Boolean userSignUp(UserDto userDto) {
        log.info("--- com.planE.user.service.UserSignUpService.UserSignUp() start ---");

        UserDto UserDto = new UserDto();
        UserDto.setEmail(UserDto.getEmail());
        UserDto.setUserPw(UserDto.getUserPw());  
        UserDto.setUserNm(UserDto.getUserNm());
        UserDto.setNickName(UserDto.getNickName());
        UserDto.setUserBirthDate(UserDto.getUserBirthDate());
        UserDto.setPhoneNum(UserDto.getPhoneNum());
        
        userSignUpRepository.insertUserSignUp(userDto);
        
        log.info("--- com.planE.user.service.UserSignUpService.UserSignUp() end ---");
		
        return true;
    }
}
