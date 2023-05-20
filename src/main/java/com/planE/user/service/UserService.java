package com.planE.user.service;

import java.util.List;

import com.planE.calendar.dto.CalendarDto;
import com.planE.calendar.repository.CalendarRepository;
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

    @Autowired
    public CalendarRepository calendarRepository;

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

          // 기본 캘린더 생성
          List<UserDto> userSearch = userRepository.userFind(userDto.getEmail());
          String userId = userSearch.get(0).getUserId();
          CalendarDto calendarDto = new CalendarDto();
          calendarDto.setUserId(userId);
          calendarDto.setCalendarName("기본 캘린더");
          calendarDto.setCalendarContent("기본 캘린더");

          calendarRepository.add(calendarDto);
          log.info("--- com.planE.user.service.UserService.AddUser() end ---");
  		
          return true;
      }

}