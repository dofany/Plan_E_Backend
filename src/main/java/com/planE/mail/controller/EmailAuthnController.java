package com.planE.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planE.mail.dto.EmailAuthnDto;
import com.planE.mail.service.EmailAuthnService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api("이메일인증")
@RestController  //ResponseBody가 없어도 json값 전송 가능
@RequestMapping("${property.api.end-point}")
@Slf4j
public class EmailAuthnController {

	@Autowired
	EmailAuthnService emailAuthnService;
	
	@ApiOperation("이메일 인증")
	@PostMapping("/mail/emailCheck/{inputEmailAuthnNum}")
	public String emailCheck(@PathVariable(required = false) String inputEmailAuthnNum, @RequestBody EmailAuthnDto emailAuthnDto) {

		log.info("--- com.planE.mail.controller.EmailAuthnController.emailAuthn() start ---");
		if (inputEmailAuthnNum == null) {
			inputEmailAuthnNum = "blank";
		}
		
		String result = emailAuthnService.emailCheck(inputEmailAuthnNum, emailAuthnDto);

		log.info("--- com.planE.mail.controller.EmailAuthnController.emailAuthn() end ---");
		
		return result;
	}
}
