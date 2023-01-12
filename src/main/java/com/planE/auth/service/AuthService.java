package com.planE.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planE.auth.dto.AuthDto;
import com.planE.auth.repository.AuthRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

	@Autowired
	private AuthRepository authRepository;
	
	public List<AuthDto> findAllAuth() {
		
		log.info("--- com.planE.auth.service.authService.findAllAuth() start ---");
		
		List<AuthDto> result = authRepository.findAllAuth();
		
		log.info("--- com.planE.auth.service.authService.findAllAuth() end ---");
		
		return result;
	}

	
	@Transactional
	public List<AuthDto> findAuth(AuthDto authDto) {
		
		log.info("--- com.planE.auth.service.authService.findAuth() start ---");
		
		List<AuthDto> result = authRepository.findAuth(authDto);
		
		log.info("--- com.planE.auth.service.authService.findAuth() end ---");
		
		return result;
	}

	
	@Transactional
	public String addAuth(AuthDto authDto) {
		
		log.info("--- com.planE.auth.service.authService.addAuth() start ---");
		
		String str = "";
		
		//모든 파라미터 존재여부 확인
		if(authDto.getAuthNm() == null || authDto.getAuthNm().isBlank()
				|| authDto.getUseYn() == null || authDto.getUseYn().isBlank()) {
			//파라미터가 없는 경우
			log.info("Fail Add Authority - Parameter Undefined");
			str = "N";
			return str;	
		}
		
		try {
			//권한추가 성공
			authRepository.addAuth(authDto);
			log.info("Success Add Authority");
			str = "Y";
		} catch (Exception e) {
			//권한추가 실패
			log.info("Fail Add Authority - Exception");
			log.info(e.getMessage());
            str = "N";
            return str;
		} finally {
			
			log.info("--- com.planE.auth.service.authService.addAuth() end ---");
		}
		
		return str;
	}

	@Transactional
	public String modifyAuth(AuthDto authDto) {
		
		log.info("--- com.planE.auth.service.authService.modifyAuth() start ---");
		
		String str = "";

		//파라미터 authId 존재여부 확인
		if(authDto.getAuthId() == null || authDto.getAuthId().isBlank()) {
			//파라미터에 authId가 없는 경우
			log.info("Fail ModifyAuth - AuthId to Modify Undefined");
			str = "N";
			return str;	
		}
		
		try {		
			//권한수정 성공
			log.info("Success Modify Authority");
			authRepository.modifyAuth(authDto);
			str = "Y";
			
		} catch (Exception e) {
			//권한추가 실패
			log.info("Fail Modify Authority - Exception");
			log.info(e.getMessage());
            str = "N";
            return str;
            
		} finally {
			
			log.info("--- com.planE.auth.service.authService.modifyAuth() end ---");
		}
		
		return str;
	}

	@Transactional
	public String removeAuth(AuthDto authDto) {
		
		log.info("--- com.planE.auth.service.authService.removeAuth() start ---");
		
		String str = "";

		//파라미터 authId 존재여부 확인
		if(authDto.getAuthId() == null || authDto.getAuthId().isBlank()) {
			//파라미터에 authId가 없는 경우
			log.info("Fail Remove Authority - AuthId to Remove Undefined");
			str = "N";
			return str;	
		}
		
		try {
			//권한삭제 성공
			log.info("Success Remove Authority");
			authRepository.removeAuth(authDto.getAuthId());
			str = "Y";
			
		} catch (Exception e) {
			//권한삭제 실패
			log.info("Fail Remove Authority - Exception");
			log.info(e.getMessage());
            str = "N";
            return str;
            
		} finally {
			
			log.info("--- com.planE.auth.service.authService.removeAuth() end ---");
		}
		
		return str;

	}

}
