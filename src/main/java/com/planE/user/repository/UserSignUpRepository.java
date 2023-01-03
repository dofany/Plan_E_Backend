package com.planE.user.repository;

import org.apache.ibatis.annotations.Mapper;

import com.planE.user.dto.UserDto;

/**
 * 회원가입 Repository
 * @author Admin
 *
 */
@Mapper
public interface UserSignUpRepository {
	public void insertUserSignUp(UserDto userDTO);
}
