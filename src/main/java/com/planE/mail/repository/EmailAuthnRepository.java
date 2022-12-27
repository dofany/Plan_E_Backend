package com.planE.mail.repository;

import org.apache.ibatis.annotations.Mapper;

import com.planE.mail.dto.EmailAuthnDto;

/**
 * 이메일인증 Repository
 * @author Admin
 *
 */
@Mapper
public interface EmailAuthnRepository {
	public void insertEmailAuthn(EmailAuthnDto emailAuthnDTO);
	
	public EmailAuthnDto selectEmailAuthn(EmailAuthnDto emailAuthnDto);
	
	public void updateEmailAuthn(EmailAuthnDto emailAuthnDto);
}
