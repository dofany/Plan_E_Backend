package com.planE.user.controller;

import com.planE.user.dto.LoginResltDto;
import com.planE.user.dto.UserCommonDto;
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
    public LoginResltDto login(@RequestBody UserLoginInputDto userLoginInputDto) {
        log.info("--- com.planE.user.controller.UserLoginController.login() start ---");
        log.info("userId :: {}, userPw :: {}", userLoginInputDto.getUserEmail(), userLoginInputDto.getUserPw());
        
        LoginResltDto result = new LoginResltDto();
        result = userLoginService.login(userLoginInputDto);
        log.info("--- com.planE.user.controller.UserLoginController.login() end ---");
        
        
        return result;
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
    
    @ApiOperation("비밀번호변경")
    @PostMapping("/user/pwChg")
    public UserCommonDto pwChg(@RequestBody UserLoginInputDto userLoginInputDto) {
    	log.info("--- com.planE.user.controller.UserLoginController.pwChg() start ---");
    	
    	Boolean result = userLoginService.pwChg(userLoginInputDto);
    	
    	UserCommonDto userCommonDto = new UserCommonDto();
    	userCommonDto.setResultYn(result);
    	
    	log.info("--- com.planE.user.controller.UserLoginController.pwChg() end ---");
    	
    	return userCommonDto;
    }
}
