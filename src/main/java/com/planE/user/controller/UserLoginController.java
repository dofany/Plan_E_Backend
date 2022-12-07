package com.planE.user.controller;

import com.planE.user.dto.UserDto;
import com.planE.user.dto.UserLoginInputDto;
import com.planE.user.service.UserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("사용자 로그인")
@RestController
@RequestMapping("${property.api.end-point}")
@Slf4j
public class UserLoginController {

    @Autowired
    UserLoginService userLoginService;

    @ApiOperation("사용자로그인인증")
    @PostMapping("/user/login")
    public String login(@RequestBody UserDto userDto) {
        log.info("--- com.planE.user.controller.UserLoginController.login() start ---");
        log.info("userId :: {}, userPw :: {}", userDto.getUserId(), userDto.getUserPw());
        log.info("--- com.planE.user.controller.UserLoginController.login() end ---");
        return "Y";
    }

    @ApiOperation("가입신청")
    @PostMapping("/user/signUp")
    public Boolean signUp(@RequestBody UserLoginInputDto userLoginInputDto) {
        log.info("--- com.planE.user.controller.UserLoginController.signUp() start ---");
        log.info(userLoginInputDto.toString());
        Boolean result = userLoginService.signUp(userLoginInputDto);
        log.info("--- com.planE.user.controller.UserLoginController.signUp() end ---");
        return result;
    }
}
