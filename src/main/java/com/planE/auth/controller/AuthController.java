package com.planE.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planE.auth.dto.AuthDto;
import com.planE.auth.service.AuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@Api("권한")
@RestController
@RequestMapping("${property.api.end-point}")
@Slf4j
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@ApiOperation("권한전체조회")
	@GetMapping("/auth/findAllAuth")
	public List<AuthDto> findAllAuth() {
		
		log.info("--- com.planE.auth.controller.authController.findAllAuth() start ---");
		
		List<AuthDto> result = authService.findAllAuth();
		
		log.info("--- com.planE.auth.controller.authController.findAllAuth() end ---");
		
		return result; 
	}
	
	@ApiOperation("권한상세조회")
	@PostMapping("/auth/findAuth")
	public List<AuthDto> findAuth(@RequestBody AuthDto authDto) {
		
		log.info("--- com.planE.auth.controller.authController.findAuth() start ---");
		
		List<AuthDto> result = authService.findAuth(authDto);
		
		log.info("--- com.planE.auth.controller.authController.findAuth() end ---");
		
		return result;
	}
	
	@ApiOperation("권한추가")
	@PostMapping("/auth/addAuth")
	public AuthDto addAuth(@RequestBody AuthDto authDto) {
		
		log.info("--- com.planE.auth.controller.authController.addAuth() start ---");
		
		String str = authService.addAuth(authDto);
		
		AuthDto result = new AuthDto();
		result.setSucesYn(str);
		
		log.info("--- com.planE.auth.controller.authController.addAuth() end ---");
		
		return result;
	}
	
	@ApiOperation("권한수정")
	@PostMapping("/auth/modifyAuth")
	public AuthDto modifyAuth(@RequestBody AuthDto authDto) {
		
		log.info("--- com.planE.auth.controller.authController.modifyAuth() start ---");
		
		String str = authService.modifyAuth(authDto);
		
		AuthDto result = new AuthDto();
		result.setSucesYn(str);
		
		log.info("--- com.planE.auth.controller.authController.modifyAuth() end ---");
		
		return result;
	}
	
	@ApiOperation("권한삭제")
	@PostMapping("/auth/removeAuth")
	public AuthDto removeAuth(@RequestBody AuthDto authDto) {
		
		log.info("--- com.planE.auth.controller.authController.removeAuth() start ---");
		
		String str = authService.removeAuth(authDto);
		
		AuthDto result = new AuthDto();
		result.setSucesYn(str);
		
		log.info("--- com.planE.auth.controller.authController.removeAuth() end ---");
		
		return result;
	}
}