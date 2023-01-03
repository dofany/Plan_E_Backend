package com.planE.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planE.user.dto.UserDto;
import com.planE.user.service.UserSignUpService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api("회원가입")
@RestController
@RequestMapping("${property.api.end-point}")
@Slf4j
public class UserSignUpController {

    @Autowired
    UserSignUpService userSignUpService;

    @ApiOperation("가입신청")
    @PostMapping("/user/userSignUp")
    public Boolean signUp(@RequestBody UserDto userDto) {
        log.info("--- com.planE.user.controller.UserSignUpController.UserSignUp() start ---");
        log.info(userDto.toString());
        Boolean result = userSignUpService.userSignUp(userDto);
        log.info("--- com.planE.user.controller.UserSignUpController.UserSignUp() end ---");
        return result;
    }
}
