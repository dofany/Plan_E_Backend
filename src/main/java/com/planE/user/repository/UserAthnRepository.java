package com.planE.user.repository;

import org.apache.ibatis.annotations.Mapper;

import com.planE.user.dto.EmailAuthnDto;
import com.planE.user.dto.UserDto;

/**
 * 이메일인증 Repository
 * @author Admin
 *
 */
@Mapper
public interface UserAthnRepository {
	public void insertEmailAuthn(EmailAuthnDto emailAuthnDTO);
	
	public EmailAuthnDto selectEmailAuthn(String email);
	
	public void updateEmailAuthn(String email);
	
	public void insertAddUser(UserDto userDTO);
}
