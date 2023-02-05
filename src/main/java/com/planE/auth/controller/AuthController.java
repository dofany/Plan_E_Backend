package com.planE.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planE.auth.dto.AuthDto;
import com.planE.auth.dto.UserAuthDto;
import com.planE.auth.service.AuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
		
		log.info("--- com.planE.auth.controller.AuthController.findAllAuth() start ---");
		
		List<AuthDto> result = authService.findAllAuth();
		
		log.info("--- com.planE.auth.controller.AuthController.findAllAuth() end ---");
		
		return result; 
	}
	
	@ApiOperation("권한상세조회")
	@PostMapping("/auth/findAuth")
	public List<AuthDto> findAuth(@RequestBody AuthDto authDto) {
		
		log.info("--- com.planE.auth.controller.AuthController.findAuth() start ---");
		
		List<AuthDto> result = authService.findAuth(authDto);
		
		log.info("--- com.planE.auth.controller.AuthController.findAuth() end ---");
		
		return result;
	}
	
	@ApiOperation("권한추가")
	@PostMapping("/auth/addAuth")
	public AuthDto addAuth(@RequestBody AuthDto authDto) {
		
		log.info("--- com.planE.auth.controller.AuthController.addAuth() start ---");
		
		String str = authService.addAuth(authDto);
		
		AuthDto result = new AuthDto();
		result.setSucesYn(str);
		
		log.info("--- com.planE.auth.controller.AuthController.addAuth() end ---");
		
		return result;
	}
	
	@ApiOperation("권한수정")
	@PostMapping("/auth/modifyAuth")
	public AuthDto modifyAuth(@RequestBody AuthDto authDto) {
		
		log.info("--- com.planE.auth.controller.AuthController.modifyAuth() start ---");
		
		String str = authService.modifyAuth(authDto);
		
		AuthDto result = new AuthDto();
		result.setSucesYn(str);
		
		log.info("--- com.planE.auth.controller.AuthController.modifyAuth() end ---");
		
		return result;
	}
	
	@ApiOperation("권한삭제")
	@PostMapping("/auth/removeAuth")
	public AuthDto removeAuth(@RequestBody AuthDto authDto) {
		
		log.info("--- com.planE.auth.controller.AuthController.removeAuth() start ---");
		
		String str = authService.removeAuth(authDto);
		
		AuthDto result = new AuthDto();
		result.setSucesYn(str);
		
		log.info("--- com.planE.auth.controller.AuthController.removeAuth() end ---");
		
		return result;
	}
	
	@ApiOperation("사용자권한 조회")
    @GetMapping("/auth/findUserAuth")
	public List<UserAuthDto> findUserAuth(@RequestBody UserAuthDto userAuthDto) {
		log.info("--- com.planE.auth.controller.AuthController.findUserAuth() start ---");
		List<UserAuthDto> result = authService.findUserAuth(userAuthDto);
		log.info("--- com.planE.auth.controller.AuthController.findUserAuth() end ---");
		return result;
	}
	
	@ApiOperation("사용자권한 추가")
    @PostMapping("/auth/addUserAuth")
    public Boolean addUserAuth(@RequestBody UserAuthDto userAuthDto) {
		log.info("--- com.planE.auth.controller.AuthController.addUserAuth() start ---");
        log.info(userAuthDto.toString());
        Boolean result = authService.addUserAuth(userAuthDto);
        log.info("--- com.planE.auth.controller.AuthController.addUserAuth() end ---");
        return result;
    }
	
	@ApiOperation("사용자권한 수정")
    @PostMapping("/auth/modifyUserAuth")
    public Boolean modifyUserAuth(@RequestBody UserAuthDto userAuthDto) {
        log.info("--- com.planE.user.controller.AuthController.modifyUserAuth() start ---");
        log.info(userAuthDto.toString());
        Boolean result = authService.modifyUserAuth(userAuthDto);
        log.info("--- com.planE.user.controller.AuthController.modifyUserAuth() end ---");
        return result;
    }
	
	@ApiOperation("사용자권한 삭제")
    @PostMapping("/auth/removeUserAuth")
    public Boolean removeUserAuth(@RequestBody UserAuthDto userAuthDto) {
        log.info("--- com.planE.user.controller.AuthController.removeUserAuth() start ---");
        log.info(userAuthDto.toString());
        Boolean result = authService.removeUserAuth(userAuthDto);
        log.info("--- com.planE.user.controller.AuthController.removeUserAuth() end ---");
        return result;
    }
}