package com.planE.user.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.planE.user.dto.UserAthnDto;
import com.planE.user.dto.UserDto;

@Mapper
//@Repository
public interface UserRepository {

    List<UserDto> userFind(String email);
    
    void lgnFailUpdate(UserDto userDto);
    
    void lgnHstInsert(UserAthnDto UserAthnDto);

    void upNewPw(UserDto userDto);
    
    public void insertAddUser(UserDto userDTO);
}