package com.planE.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planE.user.dto.UserDto;
import com.planE.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public List<UserDto> userFind(String email) {
    	
        return userRepository.userFind(email);
    }
    
    //사용자 추가
  	@Transactional
      public Boolean addUser(UserDto userDto) {
          log.info("--- com.planE.user.service.UserService.AddUser() start ---");

          UserDto UserDto = new UserDto();
          UserDto.setEmail(UserDto.getEmail());
          UserDto.setUserPw(UserDto.getUserPw());  
          UserDto.setUserNm(UserDto.getUserNm());
          
          userRepository.insertAddUser(userDto);
          
          log.info("--- com.planE.user.service.UserService.AddUser() end ---");
  		
          return true;
      }

}