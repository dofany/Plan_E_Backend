package com.planE.user.controller;

import com.planE.user.dto.UserAthnDto;
import com.planE.user.service.UserAthnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planE.user.dto.EmailAuthnDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api("사용자 인증")
@RestController  //ResponseBody가 없어도 json값 전송 가능
@RequestMapping("${property.api.end-point}")
@Slf4j
public class UserAthnController {

	@Autowired
	UserAthnService userAthnService;

	@ApiOperation("사용자 로그인 인증")
	@PostMapping("/userAthn/login")
	public UserAthnDto login(@RequestBody UserAthnDto userAthnDto) {
		log.info("--- com.planE.user.controller.UserAthnController.login() start ---");
		log.info("userId :: {}, userPw :: {}", userAthnDto.getUserEmail(), userAthnDto.getUserPw());


		UserAthnDto result = userAthnService.login(userAthnDto);
		log.info("--- com.planE.user.controller.UserAthnController.login() end ---");

		return result;
	}

	@ApiOperation("사용자 회원가입 신청")
	@PostMapping("/userAthn/signUp")
	public UserAthnDto signUp(@RequestBody UserAthnDto userAthnDto) {
		log.info("--- com.planE.user.controller.UserAthnController.signUp() start ---");
		UserAthnDto result = userAthnService.signUp(userAthnDto);
		log.info("--- com.planE.user.controller.UserAthnController.signUp() end ---");
		return result;
	}


	@ApiOperation("이메일 인증")
	@PostMapping("/userAthn/emailCheck")
	public EmailAuthnDto emailCheck(@RequestBody UserAthnDto userAthnDto) {

		log.info("--- com.planE.user.controller.UserAthnController.emailAuthn() start ---");

		EmailAuthnDto result = userAthnService.emailCheck(userAthnDto.getUserEmail(), userAthnDto.getEmailAuthnNum());

		log.info("--- com.planE.user.controller.UserAthnController.emailAuthn() end ---");

		return result;
	}

	@ApiOperation("비밀번호변경")
	@PostMapping("/userAthn/pwChg")
	public UserAthnDto pwChg(@RequestBody UserAthnDto userAthnDto) {
		log.info("--- com.planE.user.controller.UserAthnController.pwChg() start ---");

		Boolean resultYn = userAthnService.pwChg(userAthnDto);

		UserAthnDto result = new UserAthnDto();
		result.setResult(resultYn);

		log.info("--- com.planE.user.controller.UserAthnController.pwChg() end ---");

		return result;
	}

}
