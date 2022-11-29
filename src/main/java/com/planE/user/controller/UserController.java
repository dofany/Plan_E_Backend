package com.planE.user.controller;

import com.planE.user.dto.UserDto;
import com.planE.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("공통")
@RestController
@RequestMapping("${property.api.end-point}")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("Rest 호출 테스트")
    @GetMapping("/user/getUserList")
    public List<UserDto> getUserList() {
        log.info("--- com.planE.user.controller.UserController.getUserList() start ---");
        List<UserDto> userList = userService.getUserList();
        log.info("--- com.planE.user.controller.UserController.getUserList() end ---");
        return userList;
    }

}