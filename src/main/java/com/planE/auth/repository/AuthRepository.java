package com.planE.auth.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.planE.auth.dto.AuthDto;

@Mapper
public interface AuthRepository {

	List<AuthDto> findAllAuth();

	List<AuthDto> findAuth(AuthDto authDto);

	void addAuth(AuthDto authDto);

	void modifyAuth(AuthDto authDto);

	void removeAuth(String authId);

}
