package com.planE.user.repository;

import com.planE.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
//@Repository
public interface UserRepository {
    List<UserDto> getUserList();
}